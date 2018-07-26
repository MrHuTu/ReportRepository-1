package com.zdjc.report.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;




//import org.apache.catalina.realm.JNDIRealm.User;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.zdjc.report.mapper.report.AuthorityReportMapper;
import com.zdjc.report.model.AuthorityReport;
import com.zdjc.report.model.fictitious.MyPageMode;
import com.zdjc.report.model.fictitious.PageMode;
import com.zdjc.report.model.fictitious.Result;
import com.zdjc.report.service.AuthorityReportService;
import com.zdjc.report.util.DownloadUtils;
import com.zdjc.report.util.GitYmlParaUtils;
@Service
/**
 * 文件上传,下载处理类
 * @author Administrator
 *
 */
public class AuthorityReportServiceImpl implements AuthorityReportService {
	
	@Autowired
	AuthorityReportMapper authorityReportMapper;
	
	@Autowired
	GitYmlParaUtils gitYmlParaUtils;
	/**
	 * 插入
	 */
	@Override
	public void insertMasage(AuthorityReport authorityReport) {
		
		authorityReportMapper.insertMasage(authorityReport);
	}
	/**
	 * 更具项目id查询全部文件名，在文件上传的时候，不允许文件名重复
	 * @return 
	 */
	@Override
	public List<AuthorityReport> selectFileName(String projectId,String reportType,String reportName) {
				
		return authorityReportMapper.selectFileName(projectId,reportType,reportName);
	
		
	}
	/**查询对应项目类型下的可下载文件
	 * @param projectId 项目id
	 * @param reportType 报告类型 -->Day日报   Week周报
	 * 
	 */
	@Override
	public List<AuthorityReport> gitFilename(String id) {
		
		
		
		List<AuthorityReport> authorityReportList = authorityReportMapper.gitFilename(id);
		
		
				
		return authorityReportList ;
	}
	/**
	 * 文件上传
	 * 组装bean数据
	 * @throws IOException 
	 */
	@Override
	public String Cfg(MultipartRequest file, String projectId,String reportType,String timeOfReport,String personInCharge) {
		
		String message = "上传成功";
		
		Map<String, MultipartFile> multipartFileMap = file.getFileMap();
		
		Iterator<String> keySet = multipartFileMap.keySet().iterator();		
		
		//文件上传跟路径
		String root = gitYmlParaUtils.accordingOsGetParm("pdf");
		
		//已项目id，和报表类型作为根目录下面的子目录，如果该目录结构不存在，则直接创建
		//文件输出路径
		String currentPath = root+projectId+reportType+"/";
		
		File catalogue  =  new File(currentPath);
		
		if(!catalogue.isDirectory()){
			
			catalogue.mkdir();
		}
	
		while(keySet.hasNext()){
			
			String key  = keySet.next();
			
			MultipartFile multipartFile  = multipartFileMap.get(key);
			
			if(multipartFile.isEmpty()) continue;					
								
			String fileName = multipartFile.getOriginalFilename();
			
			String  fileSuffix = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
			
			if(!fileSuffix.equalsIgnoreCase("PDF")){
				
				return message = "只支持pdf文件上传";
				
			}
			
			List<AuthorityReport> nameList = selectFileName(projectId,reportType,fileName);
			
			if(nameList.size()>0) return message=fileName+":文件名重复,请重命名,或者删除服务器重名文件,再上传";
			
			byte[] bytes;
			
			try {
				//文件输出路径+文件名
				String reportPath = currentPath+fileName;
				
				bytes = multipartFile.getBytes();
				
				FileOutputStream out = new FileOutputStream(reportPath);
				
				out.write(bytes);
				
				out.close();
				
				AuthorityReport authorityReport = new AuthorityReport();
				
				authorityReport.setProjectId(projectId);
				
				authorityReport.setReportTyp(reportType);
				
				authorityReport.setReportPath(currentPath);
				
				authorityReport.setReportName(fileName);
				
				//User user = ShiroUtils.getCurrentUserFromUrl();			
				
				
				//记录上传人
				//authorityReport.setCommit_user(user.getUserName());
				
				String time = new DateTime().toString("YYYY-MM-dd HH:mm:ss");
				
				//记录上传时间，以当前系统时间为准
				authorityReport.setCommit_time(time);
				
				authorityReport.setTimeof_Report(timeOfReport);
				
				authorityReport.setPersonIn_charge(personInCharge);
				
				insertMasage(authorityReport);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				message = "系统错误，上传失败";
			}
			
			
			
			
			
		}
		
		return message;
		// TODO Auto-generated method stub
		
	}
	@Override
	public Object downFile(String id) {
		
	     Object hint =null;
		
		//实际上 projectId + reportType+reportName，已经隐形的构成了一条组件,在文件上传的时候就保证了这3个祝贺栏位得唯一性
		List<AuthorityReport> authorityReportList  = gitFilename(id);
		
		//待激活的服务器下载路径
		String path = null;
		
		//待下载文件名
		String fileName = null;
		
		if(authorityReportList!=null && authorityReportList.size()>0){
			
			AuthorityReport authorityReport = authorityReportList.get(0);
			
			
			fileName = authorityReport.getReportName();
			
			path = authorityReport.getReportPath()+fileName;
			
		}
		//ResponseEntity<byte[]>  responseEntity = null;
		if(path!=null && fileName!=null ){
			
			try {
				
				hint = DownloadUtils.downloadSolve(path);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				hint = "下载错误";
			} 
		}
		//DownloadUtils.download(fileName, filePath, response);;
		// TODO Auto-generated method stub
		return hint;
	}
	/**
	 * 查询全部上传文件信息
	 */
	@Override
	public List<PageMode> gitAllMassageByProjectId(String projectId,String currIndex ,String pageSize,String reportType) {
		//AuthorityReportMassgae authorityReportMassgae  = new AuthorityReportMassgae();
		
		int currPage = Integer.parseInt(currIndex);
		
		int pageSize_=Integer.parseInt(pageSize);
		
		String trueCurrPage = new  Integer((currPage-1)*pageSize_).toString();
		
		List<PageMode> authorityReports = authorityReportMapper.gitAllMassageByProjectId(projectId,trueCurrPage,pageSize,reportType);
		
		MyPageMode<AuthorityReport> a = authorityReportMapper.getReportAllMassage(projectId,trueCurrPage,pageSize,reportType);
		
		System.out.println(a.toString());
						
		return authorityReports;
	}
	/**
	 * 分页插方法1
	 */
	@Override
	public MyPageMode<AuthorityReport> getReportAllMassage(String projectId, String currIndex, String pageSize,String reportType) {
		
		int currPage = Integer.parseInt(currIndex);
		
		int pageSize_=Integer.parseInt(pageSize);
		
		String trueCurrPage = new  Integer((currPage-1)*pageSize_).toString();
		
		MyPageMode<AuthorityReport> myPageModes = authorityReportMapper.getReportAllMassage(projectId,trueCurrPage,pageSize,reportType);
		
		if(myPageModes==null || myPageModes==null){
			myPageModes = new MyPageMode<AuthorityReport>();
			myPageModes.setCount("0");
			List<AuthorityReport> dataList = new ArrayList<AuthorityReport>();
			myPageModes.setDataList(dataList);
			
		}
		return myPageModes;
	}
	@Override
	public Result<String> getFilename(String id) {
		
		List<AuthorityReport> authorityReport = gitFilename(id);
		
		String fileName = "";
		
		if(authorityReport!=null && authorityReport.size()==1){
			
			fileName = authorityReport.get(0).getReportName();
			
		}else{
			return new Result<String>().failure("不存在该文件", fileName);
		}
		
		return  new Result<String>().success("查询成功", fileName);
	}

}
