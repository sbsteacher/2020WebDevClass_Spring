package com.koreait.sboard;

import java.util.ArrayList;
import java.util.List;
import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.stereotype.Component;

import com.koreait.sboard.model.ManageBoardEntity;

@Component("menuPreparer")
public class MenuPreparer implements ViewPreparer  {
	
	@Override
	public void execute(Request tilesContext, AttributeContext attributeContext) {
		System.out.println(" ----- menuPreparer -----");
		ManageBoardEntity menu = new ManageBoardEntity();
		menu.setTyp(1);
		menu.setNm("기자");
		
		List<ManageBoardEntity> menuList = new ArrayList<>();
		menuList.add(menu);
		
        attributeContext.putAttribute("menuList", new Attribute(menuList), true);		
	}
}







