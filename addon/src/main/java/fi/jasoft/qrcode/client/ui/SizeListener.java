/*
 * Copyright 2013 John Ahlroos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fi.jasoft.qrcode.client.ui;

import com.vaadin.shared.communication.ServerRpc;

/**
 * Listener interface for listening to widget size changes.
 * 
 * @author John Ahlroos (www.jasoft.fi)
 */
public interface SizeListener extends ServerRpc {

	/**
	 * Called when the widget size changes
	 * 
	 * @param width
	 * 		The width in pixels of the widget
	 * @param height
	 * 		The height in pixels of the widget
	 */
	void sizeChanged(int width, int height);
}
