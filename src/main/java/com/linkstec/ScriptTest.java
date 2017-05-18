package com.linkstec;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
/**
 * 脚本引擎
 * @author 路哥
 *
 */
public class ScriptTest {

	/**
	 * @param args
	 * @throws ScriptException 
	 */
	public static void main(String[] args) throws ScriptException {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine1= manager.getEngineByName("JavaScript");
		ScriptEngine engine2 = manager.getEngineByExtension("js");
		ScriptEngine engine3 = manager.getEngineByMimeType("text/javascript");
		if (engine1 == null) {
			throw new RuntimeException("找不到JavaScript语言执行引擎。");
		}
		engine1.eval("println('Hello1!');");
		engine2.eval("println('Hello2!');");
		engine3.eval("println('Hello3!');");

	}

}
