import java.io.*;
import java.util.*;

/**
 * 读取目录及子目录下指定文件名的路径, 返回一个List
 */

public class FileViewer {

 /**
  * @param path
  *            文件路径
  * @param suffix
  *            后缀名, 为空则表示所有文件
  * @param isdepth
  *            是否遍历子目录
  * @return list
  */
 public static List<String> getListFiles(String path, String suffix, boolean isdepth) {
  List<String> lstFileNames = new ArrayList<String>();
  File file = new File(path);
  return FileViewer.listFile(lstFileNames, file, suffix, isdepth, false);
 }

 private static List<String> listFile(List<String> lstFileNames, File f, String suffix, boolean isdepth, boolean includeFilePath) {
  // 若是目录, 采用递归的方法遍历子目录  
  if (f.isDirectory()) {
   File[] t = f.listFiles();
   //System.out.println("file number = " + t.length  + " in " + f.getAbsolutePath());
   for (int i = 0; i < t.length; i++) {
    if (isdepth || t[i].isFile()) {
     listFile(lstFileNames, t[i], suffix, isdepth, includeFilePath);
    }
   }   
  } else {
   String filePath = f.getAbsolutePath();   
   if (!suffix.equals("")) {
    int begIndex = filePath.lastIndexOf("."); // 最后一个.(即后缀名前面的.)的索引
    String tempsuffix = "";

    if (begIndex != -1) {
     tempsuffix = filePath.substring(begIndex + 1, filePath.length());
     if (tempsuffix.equals(suffix)) {
          if(includeFilePath){
      		lstFileNames.add(filePath);
    	  } else {
    	  int tempBegIndex = filePath.lastIndexOf("/");
    	  String tempFileName = filePath.substring(tempBegIndex + 1, begIndex);
    	  if(tempFileName.endsWith("@2x")){
    	  	lstFileNames.add(tempFileName.substring(0, tempFileName.length() - 3));
    	  } else {
      		lstFileNames.add(tempFileName);
      	  }
     	 }
     }
    }
   } else {
    lstFileNames.add(filePath);
   }
  }
  return lstFileNames;
 }
 
  public static List<String> getListFilePaths(String path, String suffix, boolean isdepth) {
  List<String> lstFileNames = new ArrayList<String>();
  File file = new File(path);
  return FileViewer.listFile(lstFileNames, file, suffix, isdepth, true);
 }
}