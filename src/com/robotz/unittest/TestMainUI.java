package com.robotz.unittest;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.robotz.view.EditorJFrame;

public class TestMainUI {
	
	EditorJFrame frmMain;
	
	@Before
	public void setUp() throws Exception {
		frmMain = new EditorJFrame();
	}
	
	@Test
	public void testFrmMain() {
		assertTrue(frmMain instanceof EditorJFrame);
	}

}
