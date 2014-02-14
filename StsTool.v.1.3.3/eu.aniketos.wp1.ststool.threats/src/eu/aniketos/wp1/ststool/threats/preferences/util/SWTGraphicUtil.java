/*
* SWTGraphicUtil.java
* Copyright (C) 2013 SINTEF (http://www.sintef.no)
*
* Permission is hereby granted, free of charge, to any person
* obtaining a copy of this software and associated documentation
* files (the "Software"), to deal in the Software without
* restriction, including without limitation the rights to use,
* copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the
* Software is furnished to do so, subject to the following
* conditions:
*
* The above copyright notice and this permission notice shall be
* included in all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
* OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
* HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
* WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
* FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
* OTHER DEALINGS IN THE SOFTWARE.
*
* The MIT License (MIT)
* http://opensource.org/licenses/mit-license.php
*
*/
/*******************************************************************************
 * Copyright (c) 2011 Laurent CARON
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Laurent CARON (laurent.caron at gmail dot com) - Initial implementation and API
 *******************************************************************************/
package eu.aniketos.wp1.ststool.threats.preferences.util;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;

/**
 * This class is a singleton that provides useful methods
 */
public class SWTGraphicUtil {

	/**
	 * Constructor
	 */
	private SWTGraphicUtil() {
	}

	/**
	 * Dispose safely any SWT resource when a widget is disposed
	 * 
	 * @param widget widget attached to the resource
	 * @param r the resource to dispose
	 */
	public static void dispose(final Widget widget, final Resource r) {
		widget.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(final DisposeEvent e) {
				dispose(r);
			}
		});
	}

	/**
	 * Dispose safely any SWT resource
	 * 
	 * @param r the resource to dispose
	 */
	public static void dispose(final Resource r) {
		if (r != null && !r.isDisposed()) {
			r.dispose();
		}
	}

	/**
	 * Create a color that is disposed automatically
	 * @param r red component
	 * @param g green component
	 * @param b blue component
	 * @return the color
	 */
	public static Color createDisposableColor(final int r, final int g, final int b) {
		final Display display = Display.getCurrent();
		final Color color = new Color(display, r, g, b);
		display.addListener(SWT.Dispose, new Listener() {
			@Override
			public void handleEvent(final Event event) {
				if (!color.isDisposed()) {
					color.dispose();
				}
			}
		});
		return color;
	}

	/**
	 * Loads an image and create a SWT Image corresponding to this file
	 * 
	 * @param fileName file name of the image
	 * @return an image
	 * @see org.eclipse.swt.graphics.Image
	 */
	public static Image createImage(final String fileName) {
		return new Image(Display.getCurrent(), SWTGraphicUtil.class.getClassLoader().getResourceAsStream(fileName));
	}

	/**
	 * Create a reflected image of a source 
	 * Inspired by Daniel Spiewak
	 * (http://www.eclipsezone.com/eclipse/forums/t91013.html)
	 * 
	 * @param source source to be reflected
	 * @return the source image with a reflection
	 */
	public static Image createReflectedImage(final Image source) {
		if (source == null) {
			return null;
		}

		if (source.isDisposed()) {
			SWT.error(SWT.ERROR_WIDGET_DISPOSED);
		}

		// Create a new image
		final Rectangle sourceBounds = source.getBounds();
		final Image newImage = new Image(source.getDevice(), new Rectangle(0, 0, sourceBounds.width, (int) (sourceBounds.height * 1.5)));
		final GC gc = new GC(newImage);
		gc.setAdvanced(true);

		gc.drawImage(source, 0, 0);

		// Add the reflection
		final Transform t = new Transform(source.getDevice());
		t.setElements(1, 0, 0, -.5f, 0, sourceBounds.height + sourceBounds.height / 2);
		gc.setTransform(t);

		gc.drawImage(source, 0, 0);

		t.dispose();
		gc.dispose();

		// And add the alpha mask
		final ImageData imgData = newImage.getImageData();
		final int width = imgData.width;
		final int height = imgData.height;
		final byte[] alphaData = new byte[height * width];

		final byte[] noAlpha = new byte[width];
		for (int x = 0; x < width; x++) {
			noAlpha[x] = (byte) 255;
		}

		for (int y = 0; y < height; y++) {
			final byte[] alphaRow = new byte[width];
			if (y < sourceBounds.height) {
				System.arraycopy(noAlpha, 0, alphaData, y * width, width);
			} else {
				for (int x = 0; x < width; x++) {
					alphaRow[x] = (byte) (255 - 255 * y / height);
				}
				System.arraycopy(alphaRow, 0, alphaData, y * width, width);
			}

		}
		imgData.alphaData = alphaData;
		return new Image(source.getDevice(), imgData);
	}

	/**
	 * Returns a new scaled image.
	 * 
	 * @param source the image to be scaled
	 * @param newWidth new width of the image
	 * @param newHeight new height of the image
	 * @return a scaled image of the source
	 */
	public static Image resize(final Image source, final int newWidth, final int newHeight) {
		if (source == null) {
			return null;
		}

		if (source.isDisposed()) {
			SWT.error(SWT.ERROR_WIDGET_DISPOSED);
		}

		final Image scaledImage = new Image(source.getDevice(), newWidth, newHeight);
		final GC gc = new GC(scaledImage);
		gc.setAntialias(SWT.ON);
		gc.setInterpolation(SWT.HIGH);
		gc.drawImage(source, 0, 0, source.getBounds().width, source.getBounds().height, 0, 0, newWidth, newHeight);
		gc.dispose();

		return scaledImage;
	}

	/**
	 * Create a reflected and resized version of an image
	 * 
	 * @param source source image to be scaled and reflected
	 * @param newWidth new width of the scaled image
	 * @param newHeight new height of the scaled image
	 * @return the resized and reflected image
	 */
	public static Image createReflectedResizedImage(final Image source, final int newWidth, final int newHeight) {
		if (source == null) {
			return null;
		}

		if (source.isDisposed()) {
			SWT.error(SWT.ERROR_WIDGET_DISPOSED);
		}

		final Image newImage = new Image(source.getDevice(), newWidth, (int) (newHeight * 1.5));
		final GC gc = new GC(newImage);
		gc.setAntialias(SWT.ON);
		gc.setInterpolation(SWT.HIGH);
		gc.drawImage(source, 0, 0, source.getBounds().width, source.getBounds().height, 0, 0, newWidth, newHeight);

		// Add the reflection
		final Transform t = new Transform(source.getDevice());
		t.setElements(1, 0, 0, -.5f, 0, (float) (newHeight * 1.5));
		gc.setTransform(t);

		gc.drawImage(source, 0, 0, source.getBounds().width, source.getBounds().height, 0, 0, newWidth, newHeight);

		t.dispose();
		gc.dispose();

		// And add the alpha mask
		final ImageData imgData = newImage.getImageData();
		final int width = imgData.width;
		final int height = imgData.height;
		final byte[] alphaData = new byte[height * width];

		final byte[] noAlpha = new byte[width];
		for (int x = 0; x < width; x++) {
			noAlpha[x] = (byte) 255;
		}

		for (int y = 0; y < height; y++) {
			final byte[] alphaRow = new byte[width];
			if (y < newHeight) {
				System.arraycopy(noAlpha, 0, alphaData, y * width, width);
			} else {
				for (int x = 0; x < width; x++) {
					alphaRow[x] = (byte) (255 - 255 * y / height);
				}
				System.arraycopy(alphaRow, 0, alphaData, y * width, width);
			}

		}
		imgData.alphaData = alphaData;
		return new Image(source.getDevice(), imgData);

	}

	/**
	 * Center a shell on the primary monitor
	 * 
	 * @param shell shell to center
	 */
	public static void centerShell(final Shell shell) {
		final Monitor primary = shell.getDisplay().getPrimaryMonitor();
		final Rectangle bounds = primary.getBounds();
		final Rectangle rect = shell.getBounds();
		final int x = bounds.x + (bounds.width - rect.width) / 2;
		final int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
	}

	/**
	 * @param originalImageData The ImageData to be average blurred.
	 * Transparency information will be ignored.
	 * @param radius the number of radius pixels to consider when blurring
	 * image.
	 * @return A blurred copy of the image data, or null if an error occured.
	 * @author Nicholas Rajendram
	 * @see http://www.jasonwaltman.com/thesis/filter-blur.html
	 * @see http://www.blackpawn.com/texts/blur/default.html
	 */
	public static ImageData blur(final ImageData originalImageData, int radius) {
		/*
		 * This method will vertically blur all the pixels in a row at once. 
		 * This blurring is performed incrementally to each row. In order to vertically blur any given pixel, 
		 * maximally (radius * 2 + 1) pixels must be examined. Since each of these pixels exists in the same 
		 * column, they span across a series of consecutive rows. 
		 * These rows are horizontally blurred before being cached and used as input for the vertical blur. 
		 * Blurring a pixel horizontally and then vertically is equivalent to blurring the pixel with both its 
		 * horizontal and vertical neighbours at once. Pixels are blurred under the notion of a 'summing scope'. 
		 * A certain scope of pixels in a column are summed then averaged to determine a target pixel's resulting 
		 * RGB value. When the next lower target pixel is being calculated, the topmost pixel is removed from the 
		 * summing scope (by subtracting its RGB) and a new pixel is added to the bottom of the scope (by adding its RGB). 
		 * In this sense, the summing scope is moving downward.
		 */
		if (radius < 1) {
			return originalImageData;
		}

		// prepare new image data with 24-bit direct palette to hold blurred
		// copy of image
		final ImageData newImageData = new ImageData(originalImageData.width, originalImageData.height, 24, new PaletteData(0xFF, 0xFF00, 0xFF0000));
		if (radius >= newImageData.height || radius >= newImageData.width) {
			radius = Math.min(newImageData.height, newImageData.width) - 1;
		}
		// initialize cache
		final ArrayList<RGB[]> rowCache = new ArrayList<RGB[]>();
		final int cacheSize = radius * 2 + 1 > newImageData.height ? newImageData.height : radius * 2 + 1; // number of rows of imageData we cache
		int cacheStartIndex = 0; // which row of imageData the cache begins with
		for (int row = 0; row < cacheSize; row++) {
			// row data is horizontally blurred before caching
			rowCache.add(rowCache.size(), blurRow(originalImageData, row, radius));
		}

		// sum red, green, and blue values separately for averaging
		final RGB[] rowRGBSums = new RGB[newImageData.width];
		final int[] rowRGBAverages = new int[newImageData.width];
		int topSumBoundary = 0; // current top row of summed values scope
		int targetRow = 0; // row with RGB averages to be determined
		int bottomSumBoundary = 0; // current bottom row of summed values scope
		int numRows = 0; // number of rows included in current summing scope
		for (int i = 0; i < newImageData.width; i++) {
			rowRGBSums[i] = new RGB(0, 0, 0);
		}

		while (targetRow < newImageData.height) {
			if (bottomSumBoundary < newImageData.height) {
				do {
					// sum pixel RGB values for each column in our radius scope
					for (int col = 0; col < newImageData.width; col++) {
						rowRGBSums[col].red += rowCache.get(bottomSumBoundary - cacheStartIndex)[col].red;
						rowRGBSums[col].green += rowCache.get(bottomSumBoundary - cacheStartIndex)[col].green;
						rowRGBSums[col].blue += rowCache.get(bottomSumBoundary - cacheStartIndex)[col].blue;
					}
					numRows++;
					bottomSumBoundary++; // move bottom scope boundary lower
					if (bottomSumBoundary < newImageData.height && bottomSumBoundary - cacheStartIndex > radius * 2) {
						// grow cache
						rowCache.add(rowCache.size(), blurRow(originalImageData, bottomSumBoundary, radius));
					}
				} while (bottomSumBoundary <= radius); // to initialize rowRGBSums at start
			}

			if (targetRow - topSumBoundary > radius) {
				// subtract values of top row from sums as scope of summed
				// values moves down
				for (int col = 0; col < newImageData.width; col++) {
					rowRGBSums[col].red -= rowCache.get(topSumBoundary - cacheStartIndex)[col].red;
					rowRGBSums[col].green -= rowCache.get(topSumBoundary - cacheStartIndex)[col].green;
					rowRGBSums[col].blue -= rowCache.get(topSumBoundary - cacheStartIndex)[col].blue;
				}
				numRows--;
				topSumBoundary++; // move top scope boundary lower
				rowCache.remove(0); // remove top row which is out of summing
									// scope
				cacheStartIndex++;
			}

			// calculate each column's RGB-averaged pixel
			for (int col = 0; col < newImageData.width; col++) {
				rowRGBAverages[col] = newImageData.palette.getPixel(new RGB(rowRGBSums[col].red / numRows, rowRGBSums[col].green / numRows, rowRGBSums[col].blue / numRows));
			}

			// replace original pixels
			newImageData.setPixels(0, targetRow, newImageData.width, rowRGBAverages, 0);
			targetRow++;
		}
		return newImageData;
	}

	/**
	 * Average blurs a given row of image data. Returns the blurred row as a
	 * matrix of separated RGB values.
	 */
	private static RGB[] blurRow(final ImageData originalImageData, final int row, final int radius) {
		final RGB[] rowRGBAverages = new RGB[originalImageData.width]; // resulting rgb averages
		final int[] lineData = new int[originalImageData.width];
		originalImageData.getPixels(0, row, originalImageData.width, lineData, 0);
		int r = 0, g = 0, b = 0; // sum red, green, and blue values separately for averaging
		int leftSumBoundary = 0; // beginning index of summed values scope
		int targetColumn = 0; // column of RGB average to be determined
		int rightSumBoundary = 0; // ending index of summed values scope
		int numCols = 0; // number of columns included in current summing scope
		RGB rgb;
		while (targetColumn < lineData.length) {
			if (rightSumBoundary < lineData.length) {
				// sum RGB values for each pixel in our radius scope
				do {
					rgb = originalImageData.palette.getRGB(lineData[rightSumBoundary]);
					r += rgb.red;
					g += rgb.green;
					b += rgb.blue;
					numCols++;
					rightSumBoundary++;
				} while (rightSumBoundary <= radius); // to initialize summing scope at start
			}

			// subtract sum of left pixel as summing scope moves right
			if (targetColumn - leftSumBoundary > radius) {
				rgb = originalImageData.palette.getRGB(lineData[leftSumBoundary]);
				r -= rgb.red;
				g -= rgb.green;
				b -= rgb.blue;
				numCols--;
				leftSumBoundary++;
			}

			// calculate RGB averages
			rowRGBAverages[targetColumn] = new RGB(r / numCols, g / numCols, b / numCols);
			targetColumn++;
		}
		return rowRGBAverages;
	}

	/**
	 * Enable/disable all widgets of a control
	 * 
	 * @param control control to enable/disable
	 * @param enable <code>true</code> to enable, <code>false</code> to disable
	 */
	public static void enable(final Control control, final boolean enable) {
		if (control instanceof Composite) {
			for (final Control c : ((Composite) control).getChildren()) {
				enable(c, enable);
			}
		}
		control.setEnabled(enable);
	}

	/**
	 * Build a font from a given control. Useful if we just want a bold label
	 * for example
	 * 
	 * @param control control that handle the default font
	 * @param style new style
	 * @return a font with the given style
	 */
	public static Font buildFontFrom(final Control control, final int style) {
		final Font temp = control.getFont();
		final FontData[] fontData = temp.getFontData();
		if (fontData == null || fontData.length == 0) {
			return temp;
		}
		return new Font(control.getDisplay(), fontData[0].getName(), fontData[0].getHeight(), style);
	}

	/**
	 * Build a font from a given control. Useful if we just want a bold label
	 * for example
	 * 
	 * @param control control that handle the default font
	 * @param style new style
	 * @return a font with the given style
	 */
	public static Font buildFontFrom(final Control control, final int style, final int size) {
		final Font temp = control.getFont();
		final FontData[] fontData = temp.getFontData();
		if (fontData == null || fontData.length == 0) {
			return temp;
		}
		return new Font(control.getDisplay(), fontData[0].getName(), size, style);
	}
}
