/*
 * Copyright (c) 2016, Unknown Domain. All rights reserved.
 * GUN AGPLv3. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package team.unstudio.jblockly.core;

import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

import team.unstudio.jblockly.core.Block.ConnectionType;
import team.unstudio.jblockly.core.component.BlockLine.LineType;
import team.unstudio.jblockly.util.SVGFormatException;

/*
 * BlockRender
 */
public class BlockRender {
	
	/**
	 * 将一个SVG路径转换为{@link java.awt.geom.Path2d}
	 * @param svg
	 * @return {@link java.awt.geom.Path2d}
	 */
	public static Path2D getPathFromSVG(String svg){
		Path2D path = new Path2D.Double();
		List<String> svgcommands = new ArrayList<>();
		
		//获取SVG指令
		for(int i=0;i<svg.length();i++){
			if(Character.isLetter(svg.charAt(i))){
				boolean flag = false;
				for(int j=i+1;j<svg.length();j++){
					if(Character.isLetter(svg.charAt(j))){
						svgcommands.add(svg.substring(i, j));
						flag = true;
						break;
					}
				}
				if(!flag){
					svgcommands.add(svg.substring(i));
					break;
				}
			}
		}
		
		//将SVG指令添加到Path
		double tempX=0,tempY=0;
		for(String command:svgcommands){
			String args[] = command.substring(1).replaceAll(" ", ",").split(",");
			double values[] = new double[args.length-1];
			for(int i=1;i<args.length;i++){
				try{
					values[i-1] = Double.parseDouble(args[i]);
				}catch(NumberFormatException e){
					throw new SVGFormatException("Parameter error '"+args[i]+"'. SVGPath: ["+svg+"]", e);
				}
			}
			switch (Character.toUpperCase(command.charAt(0))) {
			case 'M':
				if(values.length<2) throw new SVGFormatException("Parameter error. SVGPath: ["+svg+"]");
				path.moveTo(values[0], values[1]);
				tempX = values[0];
				tempY = values[1];
				break;
			case 'L':
				if(values.length<2) throw new SVGFormatException("Parameter error. SVGPath: ["+svg+"]");
				path.lineTo(values[0], values[1]);
				tempX = values[0];
				tempY = values[1];
				break;
			case 'H':
				if(values.length<1) throw new SVGFormatException("Parameter error. SVGPath: ["+svg+"]");
				path.lineTo(values[0], tempY);
				tempX = values[0];
				break;
			case 'V':
				if(values.length<1) throw new SVGFormatException("Parameter error. SVGPath: ["+svg+"]");
				path.lineTo(tempX, values[0]);
				tempY = values[0];
				break;
			case 'Z':
				path.closePath();
				break;
			case 'C':
			case 'S':
				if(values.length<6) throw new SVGFormatException("Parameter error. SVGPath: ["+svg+"]");
				path.curveTo(values[0], values[1], values[2], values[3], values[4], values[5]);
				tempX = values[4];
				tempY = values[5];
				break;
			case 'Q':
			case 'T':
				if(values.length<4) throw new SVGFormatException("Parameter error. SVGPath: ["+svg+"]");
				path.quadTo(values[0], values[1], values[2], values[3]);
				tempX = values[2];
				tempY = values[3];
				break;
			case 'A':
				throw new SVGFormatException("Unsupported symbol \"A\". SVGPath: ["+svg+"]");
			default:
				throw new SVGFormatException("Unknown symbol '"+command+"'. SVGPath: ["+svg+"]");
			}
		}
		
		return path;
	}

	/**
	 * {@link team.unstudio.jblockly.core.BlockDescriber.ConnectionType} is
	 * None,Left,Bottom's path
	 */
	public static final String BLOCK_TOP = "M 0,0 ";

	/**
	 * {@link team.unstudio.jblockly.core.BlockDescriber.ConnectionType} is
	 * TopAndBottom,Top's path
	 */
	public static final String BLOCK_TOP_CONNECTION = "M 0,0 H 9 V 6 H 20 V 0 ";

	/**
	 * {@link team.unstudio.jblockly.core.BlockDescriber.ConnectionType} is
	 * Left's path
	 */
	public static final String BLOCK_SIDE_INSERT = "V 19 H -5 V 10 H 0 Z";

	
	public static String getBlockTop(ConnectionType type){
		switch (type) {
		case Top:
		case TopAndBottom:
			return BLOCK_TOP_CONNECTION;
		case None:
		case Bottom:
		case Left:
		default:
			return BLOCK_TOP;
		}
	}
	
	public static String getBlockSide(LineType type,int x, int y, int width, int height){
		switch (type) {
		case Insert:
			return getBlockInsertSlot(x, y, width, height);
		case Branch:
			return getBlockBranch(x, y, width, height);
		default:
			return "";
		}
	}
	
	/**
	 * get block path
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static String getBlockInsertSlot(int x, int y, int width, int height) {
		return new StringBuilder().append("V ").append(y + 9).append(" H ").append(x + width - 6).append(" V ")
				.append(y + 20).append(" H ").append(x + width).append(" ").toString();
	}
	
	/**
	 * get block fork's path
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static String getBlockBranch(int x, int y, int width, int height) {
		return new StringBuilder().append("V ").append(y) 
				.append(" H ").append(x + 29).append(" V ").append(y + 5).append(" H ").append(x + 20).append(" V ").append(y)
				.append(" H ").append(x + 10).append(" V ").append(y + height + 20).append(" H ").append(x + width).append(" ").toString();
	}

	/**
	 * {@link team.unstudio.jblockly.core.BlockDescriber.ConnectionType} is
	 * Bottom,TopAndBottom's path
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static String getBlockBottomConnection(int x, int y, int width, int height) {
		return new StringBuilder().append("H ").append(width).append(" V ").append(height).append(" H ").append(x + 19).append(" V ").append(height + 5)
				.append(" H ").append(x + 10).append(" V ").append(height).append(" H ").append(x).append(" Z").toString();
	}

	/**
	 * {@link team.unstudio.jblockly.core.BlockDescriber.ConnectionType} is
	 * None,Top,Left's path
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public static String getBlockBottom(int x, int y, int width, int height) {
		return new StringBuilder().append("H ").append(width).append(" V ").append(height).append(" H ").append(x).append(" ").toString();
	}
	
	public static String getBlockBottom(ConnectionType type,int x, int y, int width, int height) {
		switch (type) {
		case Bottom:
		case TopAndBottom:
			return getBlockBottomConnection(x, y, width, height);
		case Left:
			return getBlockBottom(x, y, width, height)+BLOCK_SIDE_INSERT;
		default:
			return getBlockBottom(x, y, width, height)+"Z";
		}
	}

}