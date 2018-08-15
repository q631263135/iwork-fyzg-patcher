package com.xxx.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class EvalBodyIncludeOrSkipBody extends TagSupport{

	@Override
	public int doStartTag() throws JspException {
		String str = pageContext.getRequest().getParameter("name");
		if (str != null) {
			return EVAL_BODY_INCLUDE;
		} else {
			return SKIP_BODY;
		}
	}
}
