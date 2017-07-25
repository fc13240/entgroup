package com.entgroup.adms.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExportExcelUtil {
	protected Logger log = LoggerFactory.getLogger(getClass());

	private HSSFWorkbook workbook = new HSSFWorkbook();//输出主体
	private String title;//文件名
	private ArrayList<String> sheetTitles = new ArrayList<>();//分页名
	private HttpServletResponse response;//请求
	private HSSFCellStyle column = workbook.createCellStyle();//列头格式
	private HSSFCellStyle main = workbook.createCellStyle();//正文格式
	private HSSFCellStyle url = workbook.createCellStyle();//超链接格式

	public ExportExcelUtil() {
		super();
		
	}

	public ExportExcelUtil(String title, HttpServletResponse response) {
		super();

		column.setWrapText(true);
		column.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		column.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		column.setBorderBottom(HSSFCellStyle.BORDER_NONE);
		column.setBorderLeft(HSSFCellStyle.BORDER_NONE);
		column.setBorderRight(HSSFCellStyle.BORDER_NONE);
		column.setBorderTop(HSSFCellStyle.BORDER_NONE);
		column.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		Font font = workbook.createFont();
		// font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		column.setFont(font);
		column.setWrapText(true);
		
		main.setFillForegroundColor(HSSFColor.WHITE.index);
		main.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		main.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		main.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		main.setBorderRight(HSSFCellStyle.BORDER_THIN);
		main.setBorderTop(HSSFCellStyle.BORDER_THIN);
		main.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		main.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		Font font2 = workbook.createFont();
		font2.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		main.setFont(font2);
		
		HSSFFont font3 = workbook.createFont();
		font3.setUnderline(HSSFFont.U_SINGLE);
		font3.setColor(HSSFColor.BLUE.index);
		url.setFillForegroundColor(HSSFColor.WHITE.index);
		url.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		url.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		url.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		url.setBorderRight(HSSFCellStyle.BORDER_THIN);
		url.setBorderTop(HSSFCellStyle.BORDER_THIN);
		url.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		url.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		font3.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		url.setFont(font3);
		this.title = title;
		this.response = response;
	}

	public HSSFWorkbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(HSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<String> getSheetTitles() {
		return sheetTitles;
	}

	public int size() {
		return sheetTitles.size();
	}

	public void setSheetTitles(ArrayList<String> sheetTitles) {
		this.sheetTitles = sheetTitles;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HSSFCellStyle getColumn() {
		return column;
	}

	public void setColumn(HSSFCellStyle column) {
		this.column = column;
	}

	public HSSFCellStyle getMain() {
		return main;
	}

	public void setMain(HSSFCellStyle main) {
		this.main = main;
	}

	/**
	 * 将数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格属性列名二维数组{英文列名，中文列头}
	 * @param dataset
	 *            List<Map>集合数据
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地或者网络中
	 */
	public void exportExcel(String title, String[][] headers,
			List<Map<String, Object>> dataset, HttpServletResponse response) {
		log.info("start------exportExcel-----" + title);
		CreationHelper factory = workbook.getCreationHelper();

		if (null == dataset) {
			return;
		}

		HSSFCellStyle style = workbook.createCellStyle();
		style.setWrapText(true);
		style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_NONE);
		style.setBorderLeft(HSSFCellStyle.BORDER_NONE);
		style.setBorderRight(HSSFCellStyle.BORDER_NONE);
		style.setBorderTop(HSSFCellStyle.BORDER_NONE);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		Font font = workbook.createFont();
		// font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		HSSFCellStyle style2 = workbook.createCellStyle();
		style.setWrapText(true);
		style2.setFillForegroundColor(HSSFColor.WHITE.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		Font font2 = workbook.createFont();
		font2.setBoldweight(Font.BOLDWEIGHT_NORMAL);
		style2.setFont(font2);

		// 分页
		int num = dataset.size() / 1000;
		int surplus = dataset.size() % 1000;
		if (surplus != 0) {
			num += 1;
		}
		for (int k = 0; k < num; k++) {
			List<Map<String, Object>> datasetX = Lists.newArrayList();
			if (k == num - 1) {
				datasetX = dataset.subList(k * 1000, dataset.size());
			} else {
				datasetX = dataset.subList(k * 1000, (k * 1000) + 1000);
			}
			HSSFSheet sheet = workbook.createSheet("第" + (k + 1) + "页");
			// sheet.setDefaultColumnWidth(15);
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell(0);
			int[] lengths = new int[headers.length];// 统计每列最长数据的长度
			String regex = "[0-9a-zA-Z\\._\\$%&\\*\\!\\s]";// 统计每列数据中数字和字母和符号的个数
			for (int i = 0; i < headers.length; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(style);
				RichTextString text = factory
						.createRichTextString(headers[i][1]);
				cell.setCellValue(text);
				String[] ary = ("," + text + ",").split(regex);
				lengths[i] = text.length() * 2 - (ary.length - 1);
			}

			String textValue = null;
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < datasetX.size(); i++) {
				map = datasetX.get(i);
				int index = 0;
				row = sheet.createRow(i + 1);
				for (int j = 0; j < headers.length; j++) {
					textValue = "无";
					if (map.get(headers[j][0])!=null) {
						textValue = map.get(headers[j][0]).toString();
					}
					if (null != textValue) {
						cell = row.createCell(index++);
						RichTextString richString = factory
								.createRichTextString(textValue);
						cell.setCellStyle(style2);
						// font3.setColor(HSSFColor.BLUE.index);
						cell.setCellValue(richString);
						String[] ary = ("," + textValue + ",").split(regex);
						int exlength = textValue.length() * 2
								- (ary.length - 1);
						lengths[j] = exlength > lengths[j] ? exlength : lengths[j];
					}
				}
				// //System.out.println(i);
			}
			for (int i = 0; i < headers.length; i++) {
				int length = (lengths[i] + 2) > 100 ? 100 : (lengths[i] + 2);
				// sheet.autoSizeColumn(i);对中文支持不好，长度不够
				sheet.setColumnWidth(i, length * 256);
			}
		}
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
		String titleX = formatter2.format(new Date()) + "-" + title + "-共"
				+ num + "页";
		// //System.out.println(titleX);
		try {
			response.reset();
			response.setContentType("application/vnd..ms-excel");
			response.setHeader("content-Disposition", "attachment;filename="
					+ URLEncoder.encode(titleX + ".xls", "utf-8"));
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			log.info("end------exportExcel-----" + titleX);
		}
	}

	/**
	 * 
	 * @Description: 构建分页信息
	 * @param sheetTitle 表的标题名
	 * @param headers 表格属性列名二维数组{英文列名，中文列头}"@_"代表超链接，"rowspan_"代表合并行，
	 * @param dataset List<Map>集合数据
	 * @return String
	 * @Creator maomao
	 * @Date: 2017-1-12上午11:12:23
	 * @Modifier: 
	 * @Remark:
	 * @Version: V1.0
	 */
	@SuppressWarnings("deprecation")
	public String buildExcel(String sheetTitle, String[][] headers,
			List<Map<String, Object>> dataset) {
		CreationHelper factory = workbook.getCreationHelper();

		if (null == dataset) {
			return "信息为空";
		}

		// 分页
		int num = dataset.size() / 1000;
		int surplus = dataset.size() % 1000;
		if (surplus != 0) {
			num += 1;
		}
		for (int k = 0; k < num; k++) {
			List<Map<String, Object>> datasetX = Lists.newArrayList();
			if (k == num - 1) {
				datasetX = dataset.subList(k * 1000, dataset.size());
			} else {
				datasetX = dataset.subList(k * 1000, (k * 1000) + 1000);
			}
			HSSFSheet sheet = workbook.createSheet(sheetTitle + ",第" + (k + 1)
					+ "页");
			sheetTitles.add(sheetTitle + ",第" + (k + 1) + "页");
			// sheet.setDefaultColumnWidth(15);
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell(0);
			int[] lengths = new int[headers.length];// 统计每列最长数据的长度
			String regex = "[0-9a-zA-Z\\._\\$%&\\*\\!\\s]";// 统计每列数据中数字和字母和符号的个数
			for (int i = 0; i < headers.length; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(column);
				RichTextString text = factory
						.createRichTextString(headers[i][1]);
				cell.setCellValue(text);
				String[] ary = ("," + text + ",").split(regex);
				lengths[i] = text.length() * 3 - (ary.length - 1);
			}

			String textValue = null;
			Map<String, Object> map = new HashMap<String, Object>();
			boolean[] Level4rowspan = null;
			Boolean rowspanMark=null;//强制合并的标志
			try {
				for (int i = 0; i < datasetX.size(); i++) {//i代表行数
					Level4rowspan = new boolean[10];
					map = datasetX.get(i);
					int index = 0;
					row = sheet.createRow(i + 1);
					for (int j = 0; j < headers.length; j++) {//j代表列数
						textValue = map.get(headers[j][0]).toString();
						if (null == textValue) {
							textValue="";
						}
						cell = row.createCell(index++);
						RichTextString richString = factory
								.createRichTextString(textValue);
						cell.setCellStyle(main);
						// font3.setColor(HSSFColor.BLUE.index);
						cell.setCellValue(richString);
						//生成超链接操作
						if (headers[j][0].matches(".*@.*")) {
							HSSFHyperlink link = new HSSFHyperlink(
                                    HSSFHyperlink.LINK_URL);
							link.setAddress(textValue);
							cell.setHyperlink(link);
							cell.setCellStyle(url);
						}
						String[] ary = ("," + textValue + ",").split(regex);
						int exlength = textValue.length() * 2
								- (ary.length - 1);
						lengths[j] = exlength > lengths[j] ? exlength
								: lengths[j];
						//进行行合并操作
						if (headers[j][0].matches(".*rowspan.*")) {
							//首先判断是否为第一个数据，若为第一个数据则不需要合并。然后判断当前数据是否与前一数据相同，若相同则不操作，若不相同则合并前一数据
							if (i!=0&&map.get(headers[j][0])!=null&&!map.get(headers[j][0]).equals(datasetX.get(i-1).get(headers[j][0]))) {
								//统计前一数据和之前数据重复次数
								int count = 1;
								while (i-1-count>=0&&datasetX.get(i-1).get(headers[j][0])!=null&&datasetX.get(i-1).get(headers[j][0]).equals(datasetX.get(i-1-count).get(headers[j][0]))) {
									count++;
								}
						        /**
						         * 合并单元格，使用addMergedRegoin函数.
						         */
						        sheet.addMergedRegion(new CellRangeAddress(i-count+1, i, j, j));
						        if (StringUtils.substringBetween(headers[j][0], "rowspan", "col")!=null) {
						        	for (int l = 0; l < Integer.parseInt(StringUtils.substringBetween(headers[j][0], "rowspan", "col")); l++) {
						        		sheet.addMergedRegion(new CellRangeAddress(i-count+1, i, j+l, j+l));
									}
								}
						        if (StringUtils.substringBetween(headers[j][0], "level", "rowspan")!=null) {
						        	Level4rowspan[Integer.parseInt(StringUtils.substringBetween(headers[j][0], "level", "rowspan"))]=true;
								}
							}
							//最后判断是否为最后一个，若为最后一个且与前一个相同则与之前数据合并
							else if (i==datasetX.size()-1) {
								int count = 1;
								rowspanMark=false;
								if (StringUtils.substringBetween(headers[j][0], "level", "rowspan")!=null) {
									for (int l = 0; l < Integer.parseInt(StringUtils.substringBetween(headers[j][0], "level", "rowspan")); l++) {
										rowspanMark=rowspanMark||Level4rowspan[l];
									}
								}
								if (rowspanMark) {
									//统计前一数据和之前数据重复次数
									count = 1;
									while (i-1-count>=0&&datasetX.get(i-1).get(headers[j][0])!=null&&datasetX.get(i-1).get(headers[j][0]).equals(datasetX.get(i-1-count).get(headers[j][0]))) {
										count++;
									}
									datasetX.get(i-1).put(headers[j][0], datasetX.get(i-1).get(headers[j][0])+""+i);
									/**
									 * 合并单元格，使用addMergedRegoin函数.
									 */
									sheet.addMergedRegion(new CellRangeAddress(i-count+1, i, j, j));
									if (StringUtils.substringBetween(headers[j][0], "rowspan", "col")!=null) {
										for (int l = 0; l < Integer.parseInt(StringUtils.substringBetween(headers[j][0], "rowspan", "col")); l++) {
											sheet.addMergedRegion(new CellRangeAddress(i-count+1, i, j+l, j+l));
										}
									}
								}
								while (i-count>=0&&datasetX.get(i).get(headers[j][0])!=null&&datasetX.get(i).get(headers[j][0]).equals(datasetX.get(i-count).get(headers[j][0]))) {
									count++;
								}
						        /**
						         * 合并单元格，使用addMergedRegoin函数.
						         */
						        sheet.addMergedRegion(new CellRangeAddress(i-count+2, i+1, j, j));
						        if (StringUtils.substringBetween(headers[j][0], "rowspan", "col")!=null) {
						        	for (int l = 0; l < Integer.parseInt(StringUtils.substringBetween(headers[j][0], "rowspan", "col")); l++) {
						        		sheet.addMergedRegion(new CellRangeAddress(i-count+1, i, j+l, j+l));
									}
								}
							}
							//当不符合上述条件但有高等级行合并时进行行合并
							else{
								rowspanMark=false;
						        if (StringUtils.substringBetween(headers[j][0], "level", "rowspan")!=null) {
						        	for (int l = 0; l < Integer.parseInt(StringUtils.substringBetween(headers[j][0], "level", "rowspan")); l++) {
						        		rowspanMark=rowspanMark||Level4rowspan[l];
						        	}
								}
						        if (rowspanMark) {
						        	//统计前一数据和之前数据重复次数
									int count = 1;
									while (i-1-count>=0&&datasetX.get(i-1).get(headers[j][0])!=null&&datasetX.get(i-1).get(headers[j][0]).equals(datasetX.get(i-1-count).get(headers[j][0]))) {
										count++;
									}
									datasetX.get(i-1).put(headers[j][0], datasetX.get(i-1).get(headers[j][0])+""+i);
							        /**
							         * 合并单元格，使用addMergedRegoin函数.
							         */
							        sheet.addMergedRegion(new CellRangeAddress(i-count+1, i, j, j));
							        if (StringUtils.substringBetween(headers[j][0], "rowspan", "col")!=null) {
							        	for (int l = 0; l < Integer.parseInt(StringUtils.substringBetween(headers[j][0], "rowspan", "col")); l++) {
							        		sheet.addMergedRegion(new CellRangeAddress(i-count+1, i, j+l, j+l));
										}
									}
								}
							}
						}
					}
					// //System.out.println(i);
				}
			} catch (Exception e) {
				//System.out.println(e);
			}
			for (int i = 0; i < headers.length; i++) {
				int length = (lengths[i] + 2) > 100 ? 100 : (lengths[i] + 2);
				// sheet.autoSizeColumn(i);对中文支持不好，长度不够
				sheet.setColumnWidth(i, length * 256);
			}
		}
		return "成功";
	}

	public void exportExcelPlus() {
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyyMMdd");
		String titleX = formatter2.format(new Date()) + "-" + title + "-共"
				+ size() + "页";
		// //System.out.println(titleX);
		try {
			response.reset();
			response.setContentType("application/vnd..ms-excel");
			response.setHeader("content-Disposition", "attachment;filename="
					+ URLEncoder.encode(titleX + ".xls", "utf-8"));
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			log.info("end------exportExcel-----" + titleX);
		}
	}
}