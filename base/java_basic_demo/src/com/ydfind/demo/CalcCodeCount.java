package com.ydfind.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class CalcCodeCount {
	private String dir;
	private long fileCount = 0; // Java类的数量
    private long nullLineCount = 0; // 空行
    private long commentLineCount = 0; // 注释行
    private long codeLineCount = 0; // 代码行
    private long allLineCount = 0; // 代码行
    private String[] fileEnds;
	
	public static void main(String[] args) {
		CalcCodeCount stat = new CalcCodeCount();
		stat.setDir("D:\\temp"); // 目录
		String[] ends = {".java", ".json"}; 
		stat.setEnds(ends);
//        String type = ".java";//查找什么类型的代码，如".java"就是查找以java开发的代码量，".php"就是查找以PHP开发的代码量
        try {
			stat.calc();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("路径：" + stat.getDir());
        System.out.println("类数量：" + stat.getFileCount());
        System.out.println("注释数量：" + stat.getCommentLineCount());
        System.out.println("空行数量：" + stat.getNullLineCount());
        if(stat.getFileCount()==0){
            System.out.println("代码平均数量:" + 0);
        }else{
            System.out.println("代码平均数量:" + stat.getCodeLineCount() / stat.getFileCount());
        }
        System.out.println("\n代码数量：" +  stat.getCodeLineCount());
        System.out.println("总 行数量：" + stat.getAllLineCount());
	}
	
	public void calc() throws Exception {
		File f = new File(dir);
		calcFiles(f, fileEnds);
	}
	/**
	 * 判断该文件是否的结尾是否在ends里面
	 * @param filename
	 * @param ends
	 * @return
	 */
	private boolean fileEndsWith(String filename, String[] ends) {
    	boolean isEnd = false;
    	for(int i = 0; i < ends.length; i++) {
    		if(ends[i].equals("") || filename.endsWith(ends[i])) {
    			isEnd = true;
    			break;
    		}
    	}
    	return isEnd;
	}
 
	private void calcFile(File f) throws Exception {
		setFileCount(getFileCount() + 1);
        BufferedReader br = null;
        boolean comment = false;
        br = new BufferedReader(new FileReader(f));
        String line = "";
        while ((line = br.readLine()) != null) {
            setAllLineCount(getAllLineCount() + 1);
            line = line.trim();
            if (line.matches("^[//s&&[^//n]]*$")) {//这一行匹配以空格开头，但不是以回车符开头，但以回车符结尾
                setNullLineCount(getNullLineCount() + 1);
            } else if (line.startsWith("/*")
                    && !line.endsWith("*/")) {//匹配以/*......*/括住的多行注释
                setCommentLineCount(getCommentLineCount() + 1);
                comment = true;
            } else if (comment) {
                setCommentLineCount(getCommentLineCount() + 1);
                if (line.endsWith("*/")) {
                    comment = false;
                }//匹配以//开头的单行注释，及以/*......*/括住的单行注释
            } else if (line.startsWith("//") || (line.startsWith("/*")&&line.endsWith("*/"))) {
                setCommentLineCount(getCommentLineCount() + 1);
            } else {//其他的就是代码行
                setCodeLineCount(getCodeLineCount() + 1);
            }
        }
        if (br != null) {
            br.close();
            br = null;
        }
	}
	
    private void calcFiles(File f, String[] ends) throws Exception {
        File[] childs = f.listFiles();
        for (int i = 0; i < childs.length; i++) {
            File item = childs[i];
            if (!item.isDirectory()) {
                if (!fileEndsWith(item.getName(), ends)) 
                	continue;
                calcFile(item);
            } else {
            	calcFiles(childs[i],ends);
            }
        }
    }



	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
	
    public long getNullLineCount() {
		return nullLineCount;
	}

	public void setNullLineCount(long nullLineCount) {
		this.nullLineCount = nullLineCount;
	}

	public long getFileCount() {
		return fileCount;
	}

	public void setFileCount(long fileCount) {
		this.fileCount = fileCount;
	}

	public long getCommentLineCount() {
		return commentLineCount;
	}

	public void setCommentLineCount(long commentLineCount) {
		this.commentLineCount = commentLineCount;
	}

	public long getCodeLineCount() {
		return codeLineCount;
	}

	public void setCodeLineCount(long codeLineCount) {
		this.codeLineCount = codeLineCount;
	}

	public long getAllLineCount() {
		return allLineCount;
	}

	public void setAllLineCount(long allLineCount) {
		this.allLineCount = allLineCount;
	}

	public String[] getFileEnds() {
		return fileEnds;
	}

	public void setEnds(String[] fileEnds) {
		this.fileEnds = fileEnds;
	}

}
