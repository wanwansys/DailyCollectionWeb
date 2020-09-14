package com.shkj.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shkj.bean.DailyReportInfo;
import com.shkj.service.DailyReportInfoService;

@RestController
@RequestMapping("/dailyReportInfo")
public class DailyReportInfoController {

	/*
	 * @Resource private DataSource dataSource;
	 */
	@Autowired
	public DailyReportInfoService dailyReportInfoService;
	
	/*
	 * @GetMapping("/query") public void query(){
	 * System.out.println("查询到的数据源连接池信息是:"+dataSource);
	 * System.out.println("查询到的数据源连接池类型是:"+dataSource.getClass());
	 * //System.out.println("查询到的数据源连接池名字是:"+dataSource.getPoolProperties().getName(
	 * )); }
	 */
	
	@RequestMapping("/getAllDailyReportInfoList")
	@ResponseBody
	public PageInfo<DailyReportInfo> getAllDailyReportInfoList(int currentPage,int pageSize,String speakTime){	
		//查看全部人员日报
		//设置分页信息，分别是当前页数和每页显示的总记录数
		Map<String, String> map = new HashMap<String, String>();
		map.put("speakTime", speakTime);
		PageHelper.startPage(currentPage, pageSize);
		List<DailyReportInfo> list = dailyReportInfoService.getAllDailyReportInfoList(map);
	    return new PageInfo<DailyReportInfo>(list);
	}
	
	@RequestMapping("/exportAllDailyReportInfo")
	public void exportAllDailyReportInfo(String speakTime,HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("speakTime", speakTime);
		List<DailyReportInfo> list = dailyReportInfoService.getAllDailyReportInfoList(map);
		
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("全部人员日报");
                             
        String fileName = "全部人员日报";//导出文件名
        
        String[] headers = {"序号","时间","部门","姓名","发言内容"};
        
        HSSFRow row = sheet.createRow(0);        
        //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        
        int rowNum = 1;
        for(int i = 0; i < list.size(); i++) {
        	HSSFRow rows = sheet.createRow(rowNum);       	
        	DailyReportInfo dailyReportInfo = list.get(i);
        	
        	rows.createCell(0).setCellValue((i+1));
        	rows.createCell(1).setCellValue(dailyReportInfo.getSpeakTime());
        	rows.createCell(2).setCellValue(dailyReportInfo.getSpeakDeptName());
        	rows.createCell(3).setCellValue(dailyReportInfo.getSpeakUserName());
        	rows.createCell(4).setCellValue(dailyReportInfo.getSpeakInfo());
        	
        	rowNum++;
        }
          
        OutputStream out = null;
        try {
        	response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"), "iso8859-1") + ".xls");
        	response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        	response.setCharacterEncoding("utf-8");
            
        	out = response.getOutputStream();
            workbook.write(out);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }

    }
	
	
	@RequestMapping("/getPersonDailyReportInfoList")
	@ResponseBody
	public PageInfo<DailyReportInfo> getPersonDailyReportInfoList(int currentPage,int pageSize,
			String speakStartTime,String speakEndTime){	
		//查看个人日报
		//设置分页信息，分别是当前页数和每页显示的总记录数
		Map<String, String> map = new HashMap<String, String>();
		map.put("speakStartTime", speakStartTime);
		map.put("speakEndTime", speakEndTime);
		PageHelper.startPage(currentPage, pageSize);
		List<DailyReportInfo> list = dailyReportInfoService.getPersonDailyReportInfoList(map);
	    return new PageInfo<DailyReportInfo>(list);
	}
	
	
	@RequestMapping("/exportPersonDailyReportInfo")
	public void exportPersonDailyReportInfo(String speakStartTime,String speakEndTime,HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("speakStartTime", speakStartTime);
		map.put("speakEndTime", speakEndTime);
		List<DailyReportInfo> list = dailyReportInfoService.getPersonDailyReportInfoList(map);
		
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("个人日报");
        
        //HSSFCellStyle style = workbook.createCellStyle();
                      
        String fileName = "个人日报";//导出文件名
        
        String[] headers = {"序号","时间","部门","姓名","发言内容"};
        
        HSSFRow row = sheet.createRow(0);        
        //在excel表中添加表头
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        
        int rowNum = 1;
        for(int i = 0; i < list.size(); i++) {
        	HSSFRow rows = sheet.createRow(rowNum);       	
        	DailyReportInfo dailyReportInfo = list.get(i);
        	
        	rows.createCell(0).setCellValue((i+1));
        	rows.createCell(1).setCellValue(dailyReportInfo.getSpeakTime());
        	rows.createCell(2).setCellValue(dailyReportInfo.getSpeakDeptName());
        	rows.createCell(3).setCellValue(dailyReportInfo.getSpeakUserName());
        	rows.createCell(4).setCellValue(dailyReportInfo.getSpeakInfo());
        	
        	rowNum++;
        }
          
        OutputStream out = null;
        try {
        	response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"), "iso8859-1") + ".xls");
        	response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        	response.setCharacterEncoding("utf-8");
            
        	out = response.getOutputStream();
            workbook.write(out);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }

    }
	
}
