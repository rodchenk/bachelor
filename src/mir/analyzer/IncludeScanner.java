package mir.analyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IncludeScanner {
	private final String path;
	private final String context;
	private final String EXTENSION = ".mir";
	private final String INCLUDE = "include ";
	
	public IncludeScanner(String path, String context) {
		this.path = path;
		this.context = context;
	}
	
	private boolean existInclude() {
		return this.context.indexOf(INCLUDE) != -1;
	}
	
	public String scan() {
		if(!existInclude()) 
			return this.context;

		final StringBuilder builder = new StringBuilder();
		final String[] lines = this.context.split(System.lineSeparator());
		
		for(int i = 0; i < lines.length; i++) {
			if(lines[i].indexOf(INCLUDE) != -1) {
				String include = lines[i].replaceFirst(INCLUDE, "").replaceAll("\"", "");
				try {
					lines[i] = new String(Files.readAllBytes(Paths.get(this.path + include + EXTENSION )), "UTF-8");
				} catch (IOException e1) {
					System.err.println("No include file " + include + "found");
				}
			}
		}
		
		for(String line: lines) builder.append(line + System.lineSeparator());
		
		return builder.toString();
	}
}
