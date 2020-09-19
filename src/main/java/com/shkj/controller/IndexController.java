package com.shkj.controller;


import java.io.UnsupportedEncodingException;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cmbchina.channel.SMCryptException;
import com.cmbchina.channel.api.SM2;
import com.shkj.bean.WhiteUser;
import com.shkj.service.WhiteUserService;


@RestController
@RequestMapping("/index")
public class IndexController {
		
	@Value("${sysParam.pubKey}")
    private String pubKeyParam;//公钥
		
	@Autowired
	public WhiteUserService whiteUserService;
		
	@RequestMapping("/VerifyData")
	@ResponseBody
	public String VerifyData(String data,String token){			
		try {//
			//String data = "eyJ5c3RJRCI6IuS4gOS6i+mAmuWPtyIsIm9yZ0lEIjoi5py65p6E57yW5Y+3IiwidGltZSI6IjIwMTUtMTItMjhUMTY6NTk6NDEiLCJpcCI6IjE5Mi4xNjguMTE1LjMyIiwic2FwSUQiOiLlkZjlt6Xlj7ciLCJlbWFpbCI6Im9tYXNfdGVzdF8wMUBjbWJjaGluYS5jb20iLCJuYW1lIjoi5aeT5ZCNIiwiYnIxIjoiMTAwMDAzIiwicGF0aE5hbWUiOiLmnLrmnoTot6/lvoTvvJov5oC76KGML1hYWOmDqC9YWFjlrqQiLCJncElEIjoiMDEwMDA0NThRMCIsInN5c0lEIjoi57O757ufaWQiLCJvc1ZlcnNpb24iOiI5LjAuMiIsInB1YlZlcnNpb24iOiIxMjI0MDEiLCJwbGF0Zm9ybSI6InBjIiwicGF0aElEIjoiLzEwMDAwMy8wMDAwMDAiLCJkZXZpY2VJRCI6IiIsIm9yZ05hbWUiOiLmnLrmnoTlkI3np7AiLCJnZW5kZXIiOiLmgKfliKsifQ==";
			//String token = "de42b1b6dc58b5fe49fc536a99fca5c15d054852b264ad9b8eba37a4b9fa9876c520351d383ede13f16f7de49ae4c9566946edff3967a93312aa2f579e9d97cb";
			//验证集成登录信息合法性，获取用户id（公钥验证）
			byte[] s = Base64.getDecoder().decode(data);
			String plainTextString = new String(s ,"utf-8");
			//将json字符串转化为JSONObject 
			JSONObject jsStr = new JSONObject().getJSONObject(plainTextString); 
			//通过getString("")分别取出里面的信息(data);			 
             //公钥字符串
             byte[] publicBytes = Base64.getDecoder().decode(pubKeyParam);
             byte[] tokenByte = converthextobytes(token);
             byte[] dataByte = data.getBytes("UTF-8");
             //用公钥进行签名验证
             int result = SM2.getInstance().CMBSM2VerifyWithSM3(publicBytes,dataByte,tokenByte);
			 if(result == 0){ 
				 //取用户ID，验证是否在白名单
	       	     String ystID = jsStr.getString("ystID"); 
	       	     WhiteUser whiteUser = whiteUserService.getWhiteUser(ystID);
	       	     if(null != whiteUser) {
	       	    	 return "{\"result\":\"success\",\"msg\":\"验证通过成功\"}";
	       	     }else {
	       	    	 return "{\"result\":\"fail\",\"msg\":\"没有权限访问\"}";
	       	     }
			 }else{ 
				 return "{\"result\":\"fail\",\"msg\":\"签名验证失败\"}";
			 }
			              
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SMCryptException e) {
			e.printStackTrace();
		}
		return "{\"result\":\"fail\",\"msg\":\"请求失败\"}";
	}
	
	public static byte[] converthextobytes(String value){
        int len=value.length()/2;
        byte[] ret = new byte[len];
        for (int i = 0; i < len; i++){
            ret[i] = (byte)(Integer.parseInt(value.substring(i * 2, (i + 1)*2), 16));
        }
        return ret;
	}
		
}
