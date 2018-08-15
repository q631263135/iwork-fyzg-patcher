package com.xxx.tag;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class LoginDateTag extends TagSupport{

	@Override
	public int doStartTag() throws JspException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd mm:HH:ss");
		String dateStr = sdf.format(new Date());
		try {
			pageContext.getOut().write(dateStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.doStartTag();
	}
}
