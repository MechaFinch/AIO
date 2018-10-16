package aio;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class AIOFile {	//New File IO code
	public AIOFile() {	//Instance for the main wrapper
	}
	
	protected BufferedReader read;			//Reader for everything
	protected PrintWriter pWrite;			//PrintWriter for writing text files
	protected BufferedOutputStream bWrite;	//BufferedOutputStream for writing other files
	protected File file;					//File to read/write from
	protected File sFile; 					//Stored file
	
	
	/*
	 * Each method will have three versions. The order is
	 * the internal protected method which does the work,
	 * a public version which uses the currently stored
	 * file, and a public version which uses the given file
	 * path
	 */
	
	//Read files
	//Read full files
	//Read to string array
	protected String[] rFullString() throws IOException {
		if(file.equals(null)) {
			throw new IOException("No file given");
		}
		
		ArrayList<String> in = new ArrayList<String>();
		
		read = new BufferedReader(new FileReader(file));
		
		while(true) {
			in.add(read.readLine());
			
			if(in.get(in.size() - 1).equals(null)) {
				in.remove(in.size() - 1);
				break;
			}
		}
		
		read.close();
		
		return in.toArray(new String[in.size()]);
	}
	
	public String[] readFullString() throws IOException {
		setIFile(sFile.getPath());
		return rFullString();
	}
	
	public String[] readFullString(String filePath) throws IOException {
		setIFile(filePath);
		return rFullString();
	}
	
	//Read to byte array
	protected byte[] rFullByte() throws IOException {
		if(file.equals(null)) {
			throw new IOException("no file given");
		}
		
		return Files.readAllBytes(Paths.get(file.getPath()));
	}
	
	public byte[] readFullByte() throws IOException {
		setIFile(sFile.getPath());
		return rFullByte();
	}
	
	public byte[] readFullByte(String filePath) throws IOException {
		setIFile(filePath);
		return rFullByte();
	}
	
	//Read parts of files
	//To string array
	protected String[] rPartString(int sp, int ep) throws IOException {
		if(file.equals(null)) {
			throw new IOException("No file given");
		}
		
		ArrayList<String> in = new ArrayList<String>();
		read = new BufferedReader(new FileReader(file));
		String s = null;
		int i = 0;
		
		while(true) {
			s = read.readLine();
			
			if(i >= sp) {
				if(i > ep) break;
				
				in.add(s);
			}
		}
		
		read.close();
		
		return in.toArray(new String[in.size()]);
	}
	
	public String[] readPartString(int sp, int ep) throws IOException {
		setIFile(sFile.getPath());
		return rPartString(sp, ep);
	}
	
	public String[] readPartString(String filePath, int sp, int ep) throws IOException {
		setIFile(filePath);
		return rPartString(sp, ep);
	}
	
	//To byte array
	protected byte[] rPartByte(int sp, int ep) throws IOException {
		if(file.equals(null)) {
			throw new IOException("No file given");
		}
		
		byte[] bi = Files.readAllBytes(Paths.get(file.getPath()));
		byte[] bo = new byte[(ep - sp) + 1];
		
		for(int i = sp; i <= ep; i++) {
			bo[i - sp] = bi[i];
		}
		
		return bo;
	}
	
	public byte[] readPartByte(int sp, int ep) throws IOException {
		setIFile(sFile.getPath());
		return rPartByte(sp, ep);
	}
	
	public byte[] readPartByte(String filePath, int sp, int ep) throws IOException {
		setIFile(filePath);
		return rPartByte(sp, ep);
	}
	
	//Read specific line
	//String
	public String readLineString(int index) throws IOException {
		return rPartString(index, index)[0];
	}
	
	public String readLineString(String filePath, int index) throws IOException {
		return readPartString(filePath, index, index)[0];
	}
	
	//Byte
	public byte readLineByte(int index) throws IOException {
		return rPartByte(index, index)[0];
	}
	
	public byte readLineByte(String filePath, int index) throws IOException {
		return readPartByte(filePath, index, index)[0];
	}
	
	//Write files
	//Write arrays
	//String
	protected void wStringArray(String[] toWrite, boolean overwrite) throws IOException {
		if(file.equals(null)) {
			throw new IOException("No file given");
		}
		
		pWrite = new PrintWriter(new BufferedWriter(new FileWriter(file, overwrite)), true);
		
		for(String s : toWrite) {
			pWrite.println(s);
		}
		
		pWrite.close();
	}
	
	public void writeStringArray(String[] toWrite, boolean overwrite) throws IOException {
		setIFile(sFile.getPath());
		wStringArray(toWrite, overwrite);
	}
	
	public void writeStringArray(String filePath, String[] toWrite, boolean overwrite) throws IOException {
		setIFile(filePath);
		wStringArray(toWrite, overwrite);
	}
	
	//Byte
	protected void wByteArray(byte[] toWrite, boolean overwrite) throws IOException {
		if(file.equals(null)) {
			throw new IOException("No file given");
		}
		
		bWrite = new BufferedOutputStream(new FileOutputStream(file));
		
		bWrite.write(toWrite);
	}
	
	public void writeByteArray(byte[] toWrite, boolean overwrite) throws IOException {
		setIFile(sFile.getPath());
		wByteArray(toWrite, overwrite);
	}
	
	public void writeByteArray(String filePath, byte[] toWrite, boolean overwrite) throws IOException {
		setIFile(filePath);
		wByteArray(toWrite, overwrite);
	}
	
	//Write single things
	//String
	public void writeString(String toWrite, boolean overwrite) throws IOException {
		writeStringArray(new String[] {toWrite}, overwrite);
	}
	
	public void writeString(String filePath, String toWrite, boolean overwrite) throws IOException {
		writeStringArray(filePath, new String[] {toWrite}, overwrite);
	}
	
	//Byte
	public void writeByte(byte toWrite, boolean overwrite) throws IOException {
		writeByteArray(new byte[] {toWrite}, overwrite);
	}
	
	public void writeByte(String filePath, byte toWrite, boolean overwrite) throws IOException {
		writeByteArray(filePath, new byte[] {toWrite}, overwrite);
	}
	
	//Other stuff
	//Set file (Internal)
	protected void setIFile(String filePath) {
		file = new File(filePath);
	}
	
	//Set file (Stored)
	public void setFile(String filePath) {
		sFile = new File(filePath);
	}
}