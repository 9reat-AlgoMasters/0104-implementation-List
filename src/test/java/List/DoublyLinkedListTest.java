package List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatCode;

class DoublyLinkedListTest {
    static DoublyLinkedList list;
    
    @BeforeEach
    void beforeEach() {
        list = new DoublyLinkedList();
        list.add(1);
        list.add(-2);
        list.add(3);
        list.add(-4);
        list.add(5);
    }
    
    @DisplayName("getFirst() 테스트")
    @Test
    void getFirstTest() {
        assertThat(list.getFirst()).isEqualTo(1);
        
    }
    
    @DisplayName("getLast() 테스트")
    @Test
    void getLastTest() {
        assertThat(list.getLast()).isEqualTo(5);
        
    }
    
    @DisplayName("size() 테스트")
    @Test
    void sizeTest() {
        assertThat(list.size()).isEqualTo(5);
        
    }
    
    @DisplayName("addFirst() 테스트")
    @Test
    void addFirstTest() {
        list.addFirst(0);
        assertThat(list.getFirst()).isEqualTo(0);
        assertThat(list.size()).isEqualTo(6);
    }
    
    @DisplayName("isEmpty() - true 테스트")
    @Test
    void isEmptyTest1() {
        ArrayList list2 = new ArrayList();
        assertThat(list2.isEmpty()).isTrue();
    }
    
    @DisplayName("isEmpty() - false 테스트")
    @Test
    void isEmptyTest2() {
        assertThat(list.isEmpty()).isFalse();
    }
    
    @DisplayName("contains() - true Test")
    @ParameterizedTest(name = "test {index}")
    @ValueSource(ints = {1, -2, 3, -4, 5})
    void containsTest1(int number) {
        assertThat(list.contains(number)).isTrue();
    }
    
    @DisplayName("contains() - false Test")
    @ParameterizedTest(name = "test {index}")
    @ValueSource(ints = {-1, 2, -3, 4, -5, 0})
    void containsTest2(int number) {
        assertThat(list.contains(number)).isFalse();
    }
    
    @DisplayName("get() - 올바른 인덱스 테스트")
    @ParameterizedTest(name = "test {index}")
    @MethodSource("listParams")
    void getTest1(int index, int number) {
        assertThat(list.get(index)).isEqualTo(number);
    }
    
    private static Stream<Arguments> listParams() {
        return Stream.of(
                Arguments.of(0, 1),
                Arguments.of(1, -2),
                Arguments.of(2, 3),
                Arguments.of(3, -4),
                Arguments.of(4, 5)
        );
    }
    
    @DisplayName("get() - 올바르지 않은 인덱스 테스트")
    @ParameterizedTest(name = "test {index}")
    @ValueSource(ints = {-1, 5, 10})
    void getTest2(int index) {
        assertThatThrownBy(() -> list.get(index))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }
    
    private static Stream<Arguments> getTest2Params() {
        return Stream.of(
                Arguments.of(0, 1),
                Arguments.of(1, -2),
                Arguments.of(2, 3),
                Arguments.of(3, -4),
                Arguments.of(4, 5)
        );
    }
    
    @DisplayName("add() - 특정 인덱스에 추가하는 테스트")
    @ParameterizedTest(name = "test {index}")
    @MethodSource("addIndexTestParams")
    void addIndexTest(int index, int number) {
        list.add(index, number);
        assertThat(list.get(index)).isEqualTo(number);
    }
    
    private static Stream<Arguments> addIndexTestParams() {
        return Stream.of(
                Arguments.of(1, 3),
                Arguments.of(4, -2)
        );
    }
    
    @DisplayName("remove() - 원소 제거 테스트")
    @Test
    void removeTest1() {
        list.remove(1);
        assertThat(list.get(1)).isEqualTo(3);
        assertThat(list.size()).isEqualTo(4);
    }
    
    @DisplayName("removeFirst() 테스트")
    @Test
    void removeFirstTest() {
        int removedValue = list.removeFirst();
        assertThat(removedValue).isEqualTo(1);
        assertThat(list.getFirst()).isEqualTo(-2);
        assertThat(list.size()).isEqualTo(4);
    }
    
    @DisplayName("removeLast() 테스트")
    @Test
    void removeLastTest() {
        int removedValue = list.removeLast();
        assertThat(removedValue).isEqualTo(5);
        assertThat(list.getLast()).isEqualTo(-4);
        assertThat(list.size()).isEqualTo(4);
    }
    
    @DisplayName("remove() - 원소 제거 후 반환값 테스트")
    @ParameterizedTest(name = "test {index}")
    @MethodSource("listParams")
    void removeTest2(int index, int number) {
        assertThat(list.remove(index)).isEqualTo(number);
    }
    
    @DisplayName("remove() - 올바르지 않은 인덱스 테스트")
    @ParameterizedTest(name = "test {index}")
    @ValueSource(ints = {-1, 5, 10})
    void removeTest3(int index) {
        assertThatThrownBy(() -> list.remove(index))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }
    
    @DisplayName("clear() 테스트")
    @Test
    void clearTest() {
        list.clear();
        assertThat(list.isEmpty()).isTrue();
    }
    
    @DisplayName("add() - 리스트의 크기 늘어남 테스트")
    @Test
    void increaseCapacityTest() {
        assertThatCode(this::addManyElements)
                .doesNotThrowAnyException();
        assertThat(list.size()).isEqualTo(10005);
    }
    
    private void addManyElements() {
        for (int i=0; i<10000; i++) {
            list.add(1);
        }
    }
}