package com.entgroup.adms.exception;

/**
 * @ClassName: ADMSException
 * @Description: 自定义异常类
 * @author mengqch
 */
public class ADMSException extends Exception
{

	private static final long serialVersionUID = 1L;
	private String errcode;
	public static final String ERR_REQUESTDATA = "200001";// 数据必填项
	public static final String ERR_INVALIDATA = "200002";// 数据有效性错误
	public static final String ERR_SYSTEM = "200003";// 系统异常
	public static final String ERR_DBERROR = "200004";// 数据库异常
	public static final String ERR_SESSIONLOST = "200005";//会话丢失

	public ADMSException(String errcode, String message)
	{
		super(message);
		this.errcode = errcode;
	}

	public ADMSException(String errcode, String message, Throwable cause)
	{
		super(message, cause);
		this.errcode = errcode;
	}

	public ADMSException(String message)
	{
		super(message);
	}

	public String getErrcode()
	{
		return errcode;
	}

}
