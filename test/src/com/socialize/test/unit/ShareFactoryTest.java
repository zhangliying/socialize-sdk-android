/*
 * Copyright (c) 2012 Socialize Inc. 
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy 
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.socialize.test.unit;

import org.json.JSONException;
import org.json.JSONObject;
import com.google.android.testing.mocking.AndroidMock;
import com.google.android.testing.mocking.UsesMocks;
import com.socialize.api.action.ShareType;
import com.socialize.entity.Share;
import com.socialize.entity.ShareFactory;

/**
 * 
 * @author Jason Polites
 * 
 */
public class ShareFactoryTest extends AbstractSocializeActionFactoryTest<Share, ShareFactory> {

	JSONObject mediumObj;

	@UsesMocks(Share.class)
	@Override
	protected Class<Share> getActionClass() {
		return Share.class;
	}

	@Override
	protected ShareFactory createFactory() {
		return new ShareFactory() {
			@Override
			public Share instantiateObject(JSONObject json) {
				return action;
			}

			@Override
			public JSONObject instantiateJSON() {
				return json;
			}
		};
	}

	@UsesMocks({ JSONObject.class })
	@Override
	protected void setupFromJSONExpectations() throws JSONException {
		final String text = "text";
		final String medium = "medium";
		final String medId = "id";

		String shareText = "share text";

		mediumObj = AndroidMock.createMock(JSONObject.class);

		AndroidMock.expect(json.isNull(text)).andReturn(false);
		AndroidMock.expect(json.has(text)).andReturn(true);

		AndroidMock.expect(json.isNull(medium)).andReturn(false);
		AndroidMock.expect(json.has(medium)).andReturn(true);
		AndroidMock.expect(json.getString(text)).andReturn(shareText);
		AndroidMock.expect(json.getJSONObject(medium)).andReturn(mediumObj);
		AndroidMock.expect(mediumObj.isNull(medId)).andReturn(false);
		AndroidMock.expect(mediumObj.has(medId)).andReturn(true);
		AndroidMock.expect(mediumObj.getInt(medId)).andReturn(ShareType.FACEBOOK.getId());

		action.setText(shareText);
		action.setShareType(ShareType.FACEBOOK);

		AndroidMock.replay(mediumObj);
	}

	@Override
	protected void doFromJSONVerify() {
		AndroidMock.verify(mediumObj);
	}

	@Override
	protected void setupToJSONExpectations() throws JSONException {

		ShareType type = ShareType.FACEBOOK;
		final String text = "Test Share";

		AndroidMock.expect(action.getText()).andReturn(text);
		AndroidMock.expect(json.put("text", text)).andReturn(json);

		AndroidMock.expect(action.getShareType()).andReturn(type);
		AndroidMock.expect(json.put("medium_name", type.getName())).andReturn(json);
		AndroidMock.expect(json.put("medium", type.getId())).andReturn(json);
	}

	@Override
	protected void doToJSONVerify() {

	}
}
