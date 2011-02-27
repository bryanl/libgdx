package com.badlogic.gdx.awesomium;
import com.sun.jna.Callback;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.WString;
/**
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.free.fr/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class WebViewListenerC extends Structure {
	public WebViewListenerC.onBeginNavigation_callback onBeginNavigation;
	public WebViewListenerC.onBeginLoading_callback onBeginLoading;
	public WebViewListenerC.onFinishLoading_callback onFinishLoading;
	public WebViewListenerC.onCallback_callback onCallback;
	public WebViewListenerC.onReceiveTitle_callback onReceiveTitle;
	public WebViewListenerC.onChangeTooltip_callback onChangeTooltip;
	public WebViewListenerC.onChangeCursor_callback onChangeCursor;
	public WebViewListenerC.onChangeKeyboardFocus_callback onChangeKeyboardFocus;
	public WebViewListenerC.onChangeTargetURL_callback onChangeTargetURL;
	public WebViewListenerC.onOpenExternalLink_callback onOpenExternalLink;
	public WebViewListenerC.onRequestDownload_callback onRequestDownload;
	public WebViewListenerC.onWebViewCrashed_callback onWebViewCrashed;
	public WebViewListenerC.onPluginCrashed_callback onPluginCrashed;
	public WebViewListenerC.onRequestMove_callback onRequestMove;
	public WebViewListenerC.onGetPageContents_callback onGetPageContents;
	public WebViewListenerC.onDOMReady_callback onDOMReady;
	public interface onBeginNavigation_callback extends Callback {
		void apply(Pointer webView, String url, WString frameName);
	};
	public interface onBeginLoading_callback extends Callback {
		void apply(Pointer webView, String url, WString frameName, int statusCode, WString mimeType);
	};
	public interface onFinishLoading_callback extends Callback {
		void apply(Pointer webView);
	};
	public interface onCallback_callback extends Callback {
		void apply(Pointer webView, WString objectName, WString callbackName, Pointer args);
	};
	public interface onReceiveTitle_callback extends Callback {
		void apply(Pointer webView, WString title, WString frameName);
	};
	public interface onChangeTooltip_callback extends Callback {
		void apply(Pointer webView, WString toolTip);
	};
	public interface onChangeCursor_callback extends Callback {
		void apply(Pointer webView, int cursor);
	};
	public interface onChangeKeyboardFocus_callback extends Callback {
		void apply(Pointer webView, int isFocused);
	};
	public interface onChangeTargetURL_callback extends Callback {
		void apply(Pointer webView, String url);
	};
	public interface onOpenExternalLink_callback extends Callback {
		void apply(Pointer webView, String url, WString source);
	};
	public interface onRequestDownload_callback extends Callback {
		void apply(Pointer webView, String url);
	};
	public interface onWebViewCrashed_callback extends Callback {
		void apply(Pointer webView);
	};
	public interface onPluginCrashed_callback extends Callback {
		void apply(Pointer webView, WString pluginName);
	};
	public interface onRequestMove_callback extends Callback {
		void apply(Pointer webView, int x, int y);
	};
	public interface onGetPageContents_callback extends Callback {
		void apply(Pointer webView, String url, WString contents);
	};
	public interface onDOMReady_callback extends Callback {
		void apply(Pointer webView);
	};
	public WebViewListenerC() {
		super();
		initFieldOrder();
	}
	
	public WebViewListenerC(WebViewListenerCStub stub) {
		super();
		initFieldOrder();
		this.onBeginLoading = stub.onBeginLoading;
		this.onBeginNavigation = stub.onBeginNavigation;
		this.onCallback = stub.onCallback;
		this.onChangeCursor = stub.onChangeCursor;
		this.onChangeKeyboardFocus = stub.onChangeKeyboardFocus;
		this.onChangeTargetURL = stub.onChangeTargetURL;
		this.onChangeTargetURL = stub.onChangeTargetURL;
		this.onChangeTooltip = stub.onChangeTooltip;
		this.onDOMReady = stub.onDOMReady;
		this.onFinishLoading = stub.onFinishLoading;
		this.onGetPageContents = stub.onGetPageContents;
		this.onOpenExternalLink = stub.onOpenExternalLink;
		this.onOpenExternalLink = stub.onOpenExternalLink;
		this.onPluginCrashed = stub.onPluginCrashed;
		this.onReceiveTitle = stub.onReceiveTitle;
		this.onRequestDownload = stub.onRequestDownload;
		this.onRequestMove = stub.onRequestMove;
		this.onWebViewCrashed = stub.onWebViewCrashed;
	}
	
	protected void initFieldOrder() {
		setFieldOrder(new java.lang.String[]{"onBeginNavigation", "onBeginLoading", "onFinishLoading", "onCallback", "onReceiveTitle", "onChangeTooltip", "onChangeCursor", "onChangeKeyboardFocus", "onChangeTargetURL", "onOpenExternalLink", "onRequestDownload", "onWebViewCrashed", "onPluginCrashed", "onRequestMove", "onGetPageContents", "onDOMReady"});
	}
	public static class ByReference extends WebViewListenerC implements Structure.ByReference {
		
	};
	public static class ByValue extends WebViewListenerC implements Structure.ByValue {
		
	};
	
	public static class WebViewListenerCStub {
		WebViewListener listener;
		
		public WebViewListenerCStub (WebViewListener listener) {
			this.listener = listener;
		}
		public WebViewListenerC.onBeginNavigation_callback onBeginNavigation = new onBeginNavigation_callback() {
			
			@Override public void apply (Pointer webView, String url, WString frameName) {
				listener.onBeginNavigation(new String(url), frameName.toString());
			}
		};
		public WebViewListenerC.onBeginLoading_callback onBeginLoading = new onBeginLoading_callback() {			
			@Override public void apply (Pointer webView, String url, WString frameName, int statusCode, WString mimeType) {
				listener.onBeginLoading(new String(url), frameName.toString(), statusCode, mimeType.toString());
			}
		};
		public WebViewListenerC.onFinishLoading_callback onFinishLoading = new onFinishLoading_callback() {
			
			@Override public void apply (Pointer webView) {
				listener.onFinishLoading();
			}
		};
		public WebViewListenerC.onCallback_callback onCallback = new onCallback_callback() {
			
			@Override public void apply (Pointer webView, WString objectName, WString callbackName, Pointer args) {
				// FIXME
				listener.onCallback(objectName.toString(), callbackName.toString(), new JSArguments());
			}
		};
		public WebViewListenerC.onReceiveTitle_callback onReceiveTitle = new onReceiveTitle_callback() {
			
			@Override public void apply (Pointer webView, WString title, WString frameName) {
				listener.onReceiveTitle(title.toString(), frameName.toString());
			}
		};
		public WebViewListenerC.onChangeTooltip_callback onChangeTooltip = new onChangeTooltip_callback() {
			
			@Override public void apply (Pointer webView, WString toolTip) {
				listener.onChangeTooltip(toolTip.toString());
			}
		};
		public WebViewListenerC.onChangeCursor_callback onChangeCursor = new onChangeCursor_callback() {
			
			@Override public void apply (Pointer webView, int cursor) {
				listener.onChangeCursor(cursor);
			}
		};
		public WebViewListenerC.onChangeKeyboardFocus_callback onChangeKeyboardFocus = new onChangeKeyboardFocus_callback() {
			
			@Override public void apply (Pointer webView, int isFocused) {
				listener.onChangeKeyboardFocus(isFocused!=0?true:false);
			}
		};
		public WebViewListenerC.onChangeTargetURL_callback onChangeTargetURL = new onChangeTargetURL_callback() {
			
			@Override public void apply (Pointer webView, String url) {
				listener.onChangeTargetURL(new String(url));
			}
		};
		public WebViewListenerC.onOpenExternalLink_callback onOpenExternalLink = new onOpenExternalLink_callback() {
			
			@Override public void apply (Pointer webView, String url, WString source) {
				listener.onOpenExternalLink(new String(url), source.toString());
			}
		};
		public WebViewListenerC.onRequestDownload_callback onRequestDownload = new onRequestDownload_callback() {
			
			@Override public void apply (Pointer webView, String url) {
				listener.onRequestDownload(new String(url));
			}
		};
		public WebViewListenerC.onWebViewCrashed_callback onWebViewCrashed = new onWebViewCrashed_callback() {
			
			@Override public void apply (Pointer webView) {
				listener.onWebViewCrashed();
			}
		};
		public WebViewListenerC.onPluginCrashed_callback onPluginCrashed = new onPluginCrashed_callback() {
			
			@Override public void apply (Pointer webView, WString pluginName) {
				listener.onPluginCrashed(pluginName.toString());
			}
		};
		public WebViewListenerC.onRequestMove_callback onRequestMove = new onRequestMove_callback() {
			
			@Override public void apply (Pointer webView, int x, int y) {
				listener.onRequestMove(x, y);
			}
		};
		public WebViewListenerC.onGetPageContents_callback onGetPageContents = new onGetPageContents_callback() {
			
			@Override public void apply (Pointer webView, String url, WString contents) {
				listener.onGetPageContents(new String(url), contents.toString());
			}
		};
		public WebViewListenerC.onDOMReady_callback onDOMReady = new onDOMReady_callback() {
			
			@Override public void apply (Pointer webView) {
				listener.onDOMReady();
			}
		};
	}
}
