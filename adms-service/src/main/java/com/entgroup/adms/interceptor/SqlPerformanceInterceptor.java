package com.entgroup.adms.interceptor;

import com.baomidou.mybatisplus.entity.CountOptimize;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.pagination.DialectFactory;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.toolkit.SqlUtils;
import com.baomidou.mybatisplus.toolkit.SystemClock;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

/**
 * <p>
 * 性能分析拦截器，用于输出每条 SQL 语句及其执行时间
 * </p>
 *
 * @author hpb
 * @Date 2017/3/1
 */
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class SqlPerformanceInterceptor implements Interceptor {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * SQL 执行最大时长，超过自动停止运行，有助于发现问题。
     */
    private long maxTime = 0;

    private boolean format = false;
    /**
     * count 优化方式
     */
    private String optimizeType = "default";

    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameterObject = invocation.getArgs()[1];
        RowBounds rowBounds = null;
        Pagination pagination = null;
        boolean isPageSql = false;
        if (invocation.getMethod().getName().equals("query")) {
            rowBounds = (RowBounds) invocation.getArgs()[2];
            if (rowBounds instanceof Pagination) {
                isPageSql = true;
                Pagination page = (Pagination) rowBounds;
                pagination = new Pagination(page.getCurrent(), page.getLimit());
            }
        }
        BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);
        Configuration configuration = mappedStatement.getConfiguration();
        StringBuilder sqlBuilder = new StringBuilder();
        if (isPageSql) {
            Pagination page = (Pagination) rowBounds;
            boolean orderBy = true;
            String dbType = GlobalConfiguration.getDbType(configuration).getDb();
            if (page.isSearchCount()) {
                CountOptimize countOptimize = SqlUtils.getCountOptimize(boundSql.getSql(), optimizeType, dbType,
                        page.isOptimizeCount());
                orderBy = countOptimize.isOrderBy();
            }
            String sql = DialectFactory.buildPaginationSql(pagination,
                    SqlUtils.concatOrderBy(boundSql.getSql(), page, orderBy), dbType, null).replaceAll("[\\s]+", " ");
            sqlBuilder.append(getSql(configuration, boundSql, sql));
        } else {
            sqlBuilder.append(getSql(configuration, boundSql, boundSql.getSql()));
        }
        String statementId = mappedStatement.getId();
        long start = SystemClock.now();
        Object result = invocation.proceed();
        long end = SystemClock.now();
        long timing = end - start;
        String sql = SqlUtils.sqlFormat(sqlBuilder.toString(), format);
        logger.info(" Time:{}ms,sql ID:{}, Execute SQL:{}", timing, statementId, sql);
        if (maxTime >= 1 && timing > maxTime) {
            logger.warn(" The SQL execution time is too large, please optimize ! ");
//            throw new MybatisPlusException(" The SQL execution time is too large, please optimize ! ");
        }
        return result;
    }

    public static String getSql(Configuration configuration, BoundSql boundSql, String sql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        sql = sql.replaceAll("[\\s]+", " ");
        if (parameterMappings != null && parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }
        }
        return sql;
    }

    private static String getParameterValue(Object obj) {
        String value;
        if (obj instanceof String) {
            value = obj != null ? "'" + obj.toString() + "'" : "''";
        } else if (obj instanceof Date) {
            if (obj instanceof java.sql.Date) {
                value = obj != null ? "'" + obj.toString() + "'" : "''";
            } else {
                DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT,
                        Locale.CHINA);
                value = obj != null ? "'" + formatter.format(obj) + "'" : "''";
            }
        } else {
            value = obj != null ? obj.toString() : "";
        }
        return value;
    }

    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    public void setProperties(Properties prop) {
        // TODO
    }

    public long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(long maxTime) {
        this.maxTime = maxTime;
    }

    public boolean isFormat() {
        return format;
    }

    public void setFormat(boolean format) {
        this.format = format;
    }

    public String getOptimizeType() {
        return optimizeType;
    }

    public void setOptimizeType(String optimizeType) {
        this.optimizeType = optimizeType;
    }
}
