package com.socialize.test.ui.comment;

import com.google.android.testing.mocking.UsesMocks;
import com.socialize.test.ui.SocializeUITestCase;
import com.socialize.ui.comment.CommentEditField;
import com.socialize.ui.util.Colors;
import com.socialize.util.DeviceUtils;
import com.socialize.util.Drawables;

@Deprecated
public class CommentEditFieldFactoryTest extends SocializeUITestCase {

	@UsesMocks ({CommentEditField.class, DeviceUtils.class, Colors.class, Drawables.class})
	public void testMake() {
//		
//		DeviceUtils deviceUtils = AndroidMock.createNiceMock(DeviceUtils.class);
//		Colors colors = AndroidMock.createNiceMock(Colors.class);
//		Drawables drawables = AndroidMock.createNiceMock(Drawables.class);
//		
//		final CommentEditField field = AndroidMock.createMock(CommentEditField.class, getContext());
//		
//		field.setLayoutParams((android.widget.LinearLayout.LayoutParams) AndroidMock.anyObject());
//		field.setOrientation(LinearLayout.HORIZONTAL);
//		field.setPadding(0, 0, 0, 0);
//		field.setEditText((EditText) AndroidMock.anyObject());
//		field.setButton((ImageButton) AndroidMock.anyObject());
//		field.addView((EditText) AndroidMock.anyObject());
//		field.addView((ImageButton) AndroidMock.anyObject());
//		
//		AndroidMock.expect(deviceUtils.getDIP(AndroidMock.anyInt())).andReturn(1).anyTimes();
//		AndroidMock.expect(colors.getColor((String)AndroidMock.anyObject())).andReturn(1).anyTimes();
//		AndroidMock.expect(drawables.getDrawable((String)AndroidMock.anyObject())).andReturn(null).anyTimes();
//		
//		AndroidMock.replay(field);
//		AndroidMock.replay(deviceUtils);
//		AndroidMock.replay(colors);
//		AndroidMock.replay(drawables);
//		
//		CommentEditFieldFactory factory = new CommentEditFieldFactory() {
//			@Override
//			protected CommentEditField newCommentEditField(Context context) {
//				return field;
//			}
//			
//		};
//		
//		factory.setDeviceUtils(deviceUtils);
//		factory.setColors(colors);
//		factory.setDrawables(drawables);
//		
//		factory.make(getContext());
//		
//		AndroidMock.verify(field);
	}
	
}
