package List;

import exceptions.CustomIndexOutOfBoundsException;
import exceptions.CustomNoSuchElementException;

public class DoublyLinkedList implements List {
    private Node head;
    private Node tail;
    private int size;

    public static class Node {
        int data;
        Node next;
        Node prev;

        Node(int n) {
            this.data = n;
            this.next = null;
            this.prev = null;
        }
    }

    // 생성자
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(int num) {
        // head노드부터 next를 따라가며 해당 노드의 data의 값이 num과 같으면 true 리턴, 아니면 false 리턴
        for (Node x = head; x != null; x = x.next) {
            if (x.data == num) {
                return true;
            }
        }
        return false;

    }

    @Override
    public int get(int index) {

        if (index < 0 || index >= size) {
            throw new CustomIndexOutOfBoundsException();
        }

        return search(index).data;
    }

    public int getFirst() {

        // head 노드가 null이면 예외처리해주고, 아니면 head노드의 data 리턴
        if (head == null) {
            throw new CustomNoSuchElementException();
        }
        return head.data;
    }

    public int getLast() {

        // getFirst()와 같은 방식
        if (tail == null) {
            throw new CustomNoSuchElementException();
        }
        return tail.data;
    }

    @Override
    public void add(int num) {

        // num값을 가지는 새로운 노드를 생성 후
        // size가 0이면 head 노드에 그 노드를 추가
        // 아니라면 마지막 노드에 새로운 노드를 링크시킨다.
        Node node = new Node(num);
        if (size == 0) {
            addFirst(num);
            return;
        }
        tail.next = node;
        node.prev = tail;
        tail = node;
        size++;
    }

    @Override
    public void add(int index, int num) {

        if (index > size || index < 0) {
            throw new CustomIndexOutOfBoundsException();
        }
        // index가 0이니~? 그럼 내려가서 addFirst하고 와
        if (index == 0) {
            addFirst(num);
            return;
        }
        // 마지막에 추가할거니~? 그럼 위로 가서 add하고 와
        if (index == size) {
            add(num);
        }
        // 중간에 추가할거니~?
        // 1. 현재 index에 있는 노드를 prev_node로 설정
        // 2. 현재 index의 뒤에 있는 노드를 next_node로 설정
        // 3. 새로운 노드를 생성하고, prev_node와 next_node의 링크를 제거 후,
        // 4. prev_node의 뒤에 새로운 노드를 링크시키고
        // 5. 새로운 노드 뒤에 next_node를 링크시킨다.
        Node prev_node = search(index - 1);
        Node next_node = prev_node.next;
        Node new_node = new Node(num);

        // 링크 제거
        prev_node.next = null;
        next_node.prev = null;

        // 링크 연결
        prev_node.next = new_node;
        new_node.prev = prev_node;
        new_node.next = next_node;
        next_node.prev = new_node;
        size++;

    }

    public void addFirst(int num) {

        // 새로운 노드를 생성하고,
        // 그 노드를 head 노드 앞에 위치시킨다.
        Node node = new Node(num);
        node.next = head;

        // head가 null이 아닐 경우에만 head노드의 뒤 링크를 새로운 노드와 연결하여줌.
        if (head != null) {
            head.prev = node; // 만약 head==null이면 오류뜰 것임
        }
        head = node; // 새로운 노드를 head라고 하고
        size++; // 사이즈업~

        // 데이터가 한 개일 경우 새 노두는 처음 시작노드 & 마지막노드임.
        if (head.next == null) {
            tail = head;
        }

    }

    public void remove() { // head를 삭제할 경우, 뒤의 노드가 있을 경우, 없을 경우가 있어서 작성한 메서드.

        // head 노드 복사본
        Node head_node = head;
        if (head_node == null) {
            throw new CustomNoSuchElementException();
        }
        // head노드의 next 노드 복사본
        Node next_node = head.next;

        // head노드를 null로 초기화
        head.data = -1;
        head.next = null;

        // head노드와 그 다음 next노드도 있을 경우에 대해서
        // next노드의 이전 링크를 초기화 해줌
        if (next_node != null) {
            next_node.prev = null;
        }
        // head노드를 next노드로 치환해주고 사이즈 마마!
        this.head = next_node;
        size--;

        // 만약 사이즈마마 해서 사이즈가 0이다? tail 없애
        if (size == 0) {
            this.tail = null;
        }
    }

    @Override
    public int remove(int index) {

        if (index < 0 || index >= size) {
            throw new CustomIndexOutOfBoundsException();
        }

        //만약 index가 0이면 위로 올라가서 remove하고와
        if (index == 0) {
            int tmp = head.data;
            this.remove();
            return tmp;
        }
        // index가 중간이나 끝이야?
        // 삭제할 노드의 이전 노드를 prev_node,
        // 그 뒤 노드를 remove_node,
        // 그 뒤 노드를 next_node라고 하자.
        // remove_node를 없애고, prev_node와 next_node를 이어줄것임.
        Node prev_node = search(index - 1);
        Node remove_node = prev_node.next;
        Node next_node = remove_node.next;

        // 먼저, remove할 노드의 data값을 챙겨놓고 리턴할 준비.
        int tmp = remove_node.data;

        // 이전 노드의 next 링크 제거
        prev_node.next = null;

        // 삭제할 노드 초기화
        remove_node.next = null;
        remove_node.prev = null;
        remove_node.data = -1;

        // 삭제할 노드가 마지막이 아닐 경우에만!
        // next_node의 prev 링크를 지워줌
        if (next_node != null) {
            next_node.prev = null;

            // 여기가 찐임
            // prev_node와 next노드를 연~~~~~~~~~결!
            next_node.prev = prev_node;
            prev_node.next = next_node;
        } else { // 만약 삭제할 노드가 마지막이면... 그냥 이전 노드를 tail로만 해주면 됨
            tail = prev_node;
        }

        size--;
        return tmp;
    }

    public int removeFirst() {

        if (head == null) {
            throw new CustomNoSuchElementException();
        }
        return remove(0); // index가 0일때 니까 위로 가봐
    }

    public int removeLast() {

        if (tail == null) {
            throw new CustomNoSuchElementException();
        }
        return remove(size - 1); // 인덱스가 size-1일테니까 위로가봐
    }

    @Override
    public void clear() {
        // head부터 시작해서 초기화해줘야하는데,
        // x.next로 돌 수가 없음 null이기 때문에.
        // 그래서 x의 next 복사본을 생성하고, x를 다 지워주고
        // x를 next로 옮겨가며 초기화해줌
        for (Node x = head; x != null; ) {
            Node tmp = x.next;
            x.data = -1;
            x.next = null;
            x.prev = null;
            x = tmp;
        }

        // 마무리
        head = tail = null;
        size = 0;
    }

    // index로 Node 찾는 메서드 추가
    private Node search(int index) {

        if (index < 0 || index >= size) {
            throw new CustomIndexOutOfBoundsException();
        }

        //next만큼 돌며 cnt를 증가, cnt와 index가 같으면 노드 던지기
        int cnt = 0;
        for (Node x = head; x != null; x = x.next) {
            if (cnt == index) {
                return x;
            }
            cnt++;
        }
        throw new CustomNoSuchElementException(); // 그래도 못찾겠음 예외 던져~
    }
}