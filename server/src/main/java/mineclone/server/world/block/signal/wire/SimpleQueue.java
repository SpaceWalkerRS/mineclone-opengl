package mineclone.server.world.block.signal.wire;

import java.util.AbstractQueue;
import java.util.Iterator;

public class SimpleQueue extends AbstractQueue<WireNode> {

	private WireNode head;
	private WireNode tail;

	private int size;

	SimpleQueue() {

	}

	@Override
	public boolean offer(WireNode node) {
		if (node == null) {
			throw new NullPointerException();
		}

		if (tail == null) {
			head = tail = node;
		} else {
			tail.simpleQueue_next = node;
			tail = node;
		}

		size++;

		return true;
	}

	@Override
	public WireNode poll() {
		if (head == null) {
			return null;
		}

		WireNode node = head;
		WireNode next = node.simpleQueue_next;

		if (next == null) {
			head = tail = null;
		} else {
			node.simpleQueue_next = null;
			head = next;
		}

		size--;

		return node;
	}

	@Override
	public WireNode peek() {
		return head;
	}

	@Override
	public void clear() {
		for (WireNode node = head; node != null; ) {
			WireNode n = node;
			node = node.simpleQueue_next;

			n.simpleQueue_next = null;
		}

		head = null;
		tail = null;

		size = 0;
	}

	@Override
	public Iterator<WireNode> iterator() {
		return new SimpleIterator();
	}

	@Override
	public int size() {
		return size;
	}

	private class SimpleIterator implements Iterator<WireNode> {

		private WireNode curr;
		private WireNode next;

		private SimpleIterator() {
			next = head;
		}

		@Override
		public boolean hasNext() {
			if (next == null && curr != null) {
				next = curr.simpleQueue_next;
			}

			return next != null;
		}

		@Override
		public WireNode next() {
			curr = next;
			next = curr.simpleQueue_next;

			return curr;
		}
	}
}
