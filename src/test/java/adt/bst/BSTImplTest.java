package adt.bst;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class BSTImplTest {
	
	BSTImpl<Integer> tree;
	
	@Before
	public void inicia() {
		tree = new BSTImpl<>();
	}
	
	private void fillTree() {
		Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40 };
		for (int i : array) {
			tree.insert(i);
		}
	}
	
	@Test
	public void testHeight() {
		fillTree();
		assertEquals(4, tree.height());
		tree.insert(-10);
		tree.insert(-20);
		assertEquals(6, tree.height());
		tree.insert(1);
		tree.insert(-1);
		assertEquals(6, tree.height());
	}

	@Test
	public void testSearch() {
		assertEquals(new BSTNode<>(), tree.search(null));
		assertEquals(new BSTNode<>(), tree.search(76));
		fillTree();
		assertEquals((Integer) 76, tree.search(76).getData());
		assertEquals((Integer) 232, tree.search(232).getData());
		
		assertEquals(new BSTNode<>(), tree.search(555555));
	}

	@Test
	public void testInsert() {
		fillTree();
		tree.insert(11);
		assertEquals((Integer) 11, tree.search(11).getData());
		tree.insert(4444444);
		assertEquals((Integer) 4444444, tree.search(4444444).getData());
		
		Integer[] array = new Integer[] { 6, -34, -40, 5, 2, 0, 23, 9, 12, 11, 76, 67, 232, 4444444 };
		assertArrayEquals(array, tree.preOrder());
		
		tree.insert(6);
		array = new Integer[] { 6, -34, -40, 5, 2, 0, 23, 9, 6, 12, 11, 76, 67, 232, 4444444 };
		assertArrayEquals(array, tree.preOrder());
	}

	@Test
	public void testMaximumMinimum() {
		assertNull(tree.minimum());
		assertNull(tree.maximum());
		
		fillTree();
		assertEquals((Integer) (-40), tree.minimum().getData());
		assertEquals((Integer) 232, tree.maximum().getData());
		
		tree.remove((Integer) (-40));
		tree.remove((Integer) (-34));
		assertEquals((Integer) 0, tree.minimum().getData());
		tree.remove(0);
		tree.remove(2);
		tree.remove(5);
		assertEquals((Integer) 6, tree.minimum().getData());
		tree.remove(232);
		assertEquals((Integer) 76, tree.maximum().getData());
		tree.insert(8888);
		assertEquals((Integer) 8888, tree.maximum().getData());
	}

	@Test
	public void testSucessorPredecessor() {
		assertNull(tree.sucessor((Integer) 2));
		assertNull(tree.predecessor((Integer) 5));
		
		tree.insert(18);
		assertNull(tree.predecessor((Integer) 18));
		assertNull(tree.sucessor((Integer) 18));
		tree.insert(9);
		tree.insert(22);
		
		assertEquals((Integer) 9, tree.predecessor((Integer) 18).getData());
		assertEquals((Integer) 22, tree.sucessor((Integer) 18).getData());
		assertEquals((Integer) 18, tree.sucessor((Integer) 9).getData());
		assertEquals((Integer) 18, tree.predecessor((Integer) 22).getData());
		
		tree.insert((Integer) 9);
		assertEquals((Integer) 9, tree.sucessor((Integer) 9).getData());
		assertNull(tree.predecessor((Integer) 9));
		
		Integer[] array = new Integer[] { 18, 9, 9, 22 };
		assertArrayEquals(array, tree.preOrder());
		
		assertNull(tree.predecessor(null));
		assertNull(tree.sucessor(null));
	}

	@Test
	public void testRemove() {
		tree.remove((Integer) 18);
		tree.remove(null);
		
		fillTree();
		Integer[] array = new Integer[] { 6, -34, -40, 5, 2, 0, 23, 9, 12, 76, 67, 232 };
		assertArrayEquals(array, tree.preOrder());
		
		tree.remove(-34);
		tree.remove(23);
		array = new Integer[] { 6, 0, -40, 5, 2, 67, 9, 12, 76, 232 };
		
		tree.remove(232);
		tree.remove(232);
		array = new Integer[] { 6, 0, -40, 5, 2, 67, 9, 12, 76 };
		assertArrayEquals(array, tree.preOrder());
		
		int size = 9; 
		while (!tree.isEmpty()) {
			tree.remove(tree.getRoot().getData());
			assertEquals(--size, tree.size());
		}
		
		tree.remove((Integer) 9);
		
	}

}
















