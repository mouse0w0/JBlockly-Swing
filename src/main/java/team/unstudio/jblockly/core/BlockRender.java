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

import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

/*
 * BlockRender
 */
public class BlockRender {
	
	public static GeneralPath getPathFromSVG(String svg){
		//TODO:SVG->Path
		GeneralPath path = new GeneralPath();
		List<String> svgcommands = new ArrayList<>();
		
		//获取SVG指令
		for(int i=0;i<svg.length();i++){
			if(Character.isLetter(svg.charAt(i))){
				for(int j=i+1;j<svg.length();j++){
					if(Character.isLetter(svg.charAt(j))){
						svgcommands.add(svg.substring(i, j));
						i=j-1;
					}else if(j==svg.length()+1){
						svgcommands.add(svg.substring(i));
						i=j;
					}
				}
			}
		}
		
		//将SVG指令添加到Path
		for(String command:svgcommands){
			switch (Character.toLowerCase(command.charAt(0))) {
			case 'M':
				
				break;
			case 'L':
				
				break;
			default:
				break;
			}
		}
		
		return path;
	}

	/**
	 * {@link team.unstudio.jblockly.core.BlockDescriber.ConnectionType} is
	 * None,Left,Bottom's path
	 */
	public static Path2D getBlockTop(){
		Path2D path = new Path2D.Double();
		path.moveTo(0, 0);
		return path;
	}

	/**
	 * {@link team.unstudio.jblockly.core.BlockDescriber.ConnectionType} is
	 * TopAndBottom,Top's path
	 */
	public static Path2D getBlockTopConnection(){
		Path2D path = new Path2D.Double();
		path.moveTo(0, 0);
		path.lineTo(9, 0);
		path.lineTo(9, 6);
		path.lineTo(20, 6);
		path.lineTo(20, 0);
		return path;
	}

	/**
	 * {@link team.unstudio.jblockly.core.BlockDescriber.ConnectionType} is
	 * Left's path
	 */
	public static Path2D getBlockSideInsert(){
		Path2D path = new Path2D.Double();
		path.moveTo(0, 19);
		path.lineTo(-5, 19);
		path.lineTo(-5, 10);
		path.lineTo(0, 10);
		return path;
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
		return new StringBuilder().append("V ").append(y).append(" H ").append(x + 19).append(" V ").append(y + 5)
				.append(" H ").append(x + 10).append(" V ").append(y).append(" H ").append(x).append(" Z ").toString();
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
		return new StringBuilder().append("V ").append(y).append(" H ").append(x).append(" Z ").toString();
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

}