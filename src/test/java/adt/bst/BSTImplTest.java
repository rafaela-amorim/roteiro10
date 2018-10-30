package adt.bst;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BSTImplTest {
	
	BSTImpl<Integer> tree;
	
	@Before
	public void inicia() {
		tree = new BSTImpl<>();
	}
	
	@Test
	public void testHeight() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearch() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsert() {
		tree.insert(5);
		assertEquals((Integer) 5, tree.search(5).getData());
	}

	@Test
	public void testMaximumMinimum() {
		fail("Not yet implemented");
	}

	@Test
	public void testSucessorPredecessor() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

}
