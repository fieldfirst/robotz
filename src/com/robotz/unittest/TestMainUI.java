package com.robotz.unittest;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.robotz.view.FrmMain;

public class TestMainUI {
	
	FrmMain frmMain;
	
	@Before
	public void setUp() throws Exception {
		frmMain = new FrmMain();
	}
	
	@Test
	public void testFrmMain() {
		assertTrue(frmMain instanceof FrmMain);
	}

}
