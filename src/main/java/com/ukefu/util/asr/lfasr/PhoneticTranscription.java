package com.ukefu.util.asr.lfasr;

import com.ukefu.webim.web.model.QualityConfig;
import com.ukefu.webim.web.model.StatusEvent;
import com.ukefu.webim.web.model.User;

public interface PhoneticTranscription {
	
	//科大讯飞 - 语音转写
	public void getLfasr(User user ,StatusEvent statusEvent,String local_file, QualityConfig qcConfig);
	//百度语音 - 语音转写
	public void getBaiduBce();
}
