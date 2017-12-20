package com.acn.acit.watsonservices.speechtotext;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeCallback;

public class SpeechToTextMain {

	public static void main(String[] args) {
		SpeechToText service = new SpeechToText();
		service.setUsernameAndPassword("d47667eb-7bc8-4049-9b1a-05133df1213c", "sxj7881Gwtuu");

		RecognizeOptions options = new RecognizeOptions.Builder()
		  .model("en-US_BroadbandModel").contentType("audio/flac")
		  .interimResults(true).maxAlternatives(3)
		  .keywords(new String[]{"colorado", "tornado", "tornadoes"})
		  .keywordsThreshold(0.5).build();

		BaseRecognizeCallback callback = new BaseRecognizeCallback() {
		  @Override
		  public void onTranscription(SpeechResults speechResults) {
		    System.out.println(speechResults);
		  }

		  @Override
		  public void onDisconnected() {
		    System.exit(0);
		  }
		};

		try {
		  service.recognizeUsingWebSocket
		    (new FileInputStream("audio-file.flac"), options, callback);
		}
		catch (FileNotFoundException e) {
		  e.printStackTrace();
		}

	}

}
