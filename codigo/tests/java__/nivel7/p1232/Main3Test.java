package java__.nivel7.p1232;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/** Regression tests runnable with java java__.nivel7.p1232.Main3Test. */
public final class Main3Test {
    private static final String MOVES = "FRDBULfrdbulMSms";
    private static final String SCRAMBLE = "FRuBDlFDRbU";
    private static final Class<?> CUBE;
    private static final Class<?> MOVEMENTS;
    private static final Field STATE;
    private static final Method ROTATE;
    private static final Method INITIALIZE;
    private static final Method SOLVED;
    private static final Method MOVE;

    static {
        try {
            CUBE = Class.forName("java__.nivel7.p1232.Main3$Cubo");
            MOVEMENTS = Class.forName("java__.nivel7.p1232.Main3$Cubo$Movimentos");
            STATE = CUBE.getDeclaredField("cubo");
            STATE.setAccessible(true);
            ROTATE = method(CUBE, "rotate", int[][].class, boolean.class);
            INITIALIZE = method(CUBE, "preenche");
            SOLVED = method(CUBE, "isResolvido");
            MOVE = method(MOVEMENTS, "move");
        } catch (ReflectiveOperationException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void main(String[] args) throws Exception {
        matrixRotation();
        frontClockwise();
        rightClockwise();
        downClockwise();
        cyclesAndInverses();
        oppositeFacesCommute();
        solvedInitialization();
        commandLine();
        System.out.println("Main3Test: OK");
    }

    private static void matrixRotation() throws Exception {
        int[][] original = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
        int[][] clockwise = rotate(original, true);
        check(Arrays.deepEquals(clockwise, new int[][]{{6, 3, 0}, {7, 4, 1}, {8, 5, 2}}),
                "rotate(true) must turn the matrix clockwise");
        check(Arrays.deepEquals(rotate(original, false),
                new int[][]{{2, 5, 8}, {1, 4, 7}, {0, 3, 6}}),
                "rotate(false) must turn the matrix counterclockwise");
        check(Arrays.deepEquals(original, new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}}),
                "rotate must preserve its input");
        check(Arrays.deepEquals(original, rotate(clockwise, false)),
                "opposite matrix rotations must cancel");
    }

    // Face order: F, R, B, L, U, D. Each face is viewed from outside the cube.
    // Distinct sticker labels expose reversals that uniform face colors conceal.
    private static void frontClockwise() throws Exception {
        setState(labeledCube());
        move('F');
        assertState(new int[][][]{
                {{20, 10, 0}, {21, 11, 1}, {22, 12, 2}},
                {{420, 101, 102}, {421, 111, 112}, {422, 121, 122}},
                {{200, 201, 202}, {210, 211, 212}, {220, 221, 222}},
                {{300, 301, 500}, {310, 311, 501}, {320, 321, 502}},
                {{400, 401, 402}, {410, 411, 412}, {322, 312, 302}},
                {{120, 110, 100}, {510, 511, 512}, {520, 521, 522}}
        }, "F sticker orientation");

        move('F');
        assertState(new int[][][]{
                {{22, 21, 20}, {12, 11, 10}, {2, 1, 0}},
                {{322, 101, 102}, {312, 111, 112}, {302, 121, 122}},
                {{200, 201, 202}, {210, 211, 212}, {220, 221, 222}},
                {{300, 301, 120}, {310, 311, 110}, {320, 321, 100}},
                {{400, 401, 402}, {410, 411, 412}, {502, 501, 500}},
                {{422, 421, 420}, {510, 511, 512}, {520, 521, 522}}
        }, "second F must use the current stickers");
    }

    private static void rightClockwise() throws Exception {
        setState(labeledCube());
        move('R');
        assertState(new int[][][]{
                {{0, 1, 502}, {10, 11, 512}, {20, 21, 522}},
                {{120, 110, 100}, {121, 111, 101}, {122, 112, 102}},
                {{422, 201, 202}, {412, 211, 212}, {402, 221, 222}},
                {{300, 301, 302}, {310, 311, 312}, {320, 321, 322}},
                {{400, 401, 2}, {410, 411, 12}, {420, 421, 22}},
                {{500, 501, 220}, {510, 511, 210}, {520, 521, 200}}
        }, "R sticker orientation");
    }

    private static void downClockwise() throws Exception {
        setState(labeledCube());
        move('D');
        assertState(new int[][][]{
                {{0, 1, 2}, {10, 11, 12}, {320, 321, 322}},
                {{100, 101, 102}, {110, 111, 112}, {20, 21, 22}},
                {{200, 201, 202}, {210, 211, 212}, {120, 121, 122}},
                {{300, 301, 302}, {310, 311, 312}, {220, 221, 222}},
                {{400, 401, 402}, {410, 411, 412}, {420, 421, 422}},
                {{520, 510, 500}, {521, 511, 501}, {522, 512, 502}}
        }, "D sticker orientation");
    }

    private static void cyclesAndInverses() throws Exception {
        setState(labeledCube());
        apply(SCRAMBLE);
        int[][][] scrambled = copyState();
        int[] stickers = sortedStickers(scrambled);
        for (char movement : MOVES.toCharArray()) {
            setState(copy(scrambled));
            for (int turn = 1; turn <= 4; turn++) {
                move(movement);
                check(Arrays.equals(stickers, sortedStickers(state())),
                        movement + " must conserve every sticker after turn " + turn);
            }
            assertState(scrambled, "four " + movement + " turns must restore a scrambled cube");

            move(movement);
            move(inverse(movement));
            assertState(scrambled, movement + " followed by its inverse");
        }

        String sequence = "FRDBULMSfrdbulmsFRuRDf";
        apply(sequence);
        for (int i = sequence.length() - 1; i >= 0; i--) {
            move(inverse(sequence.charAt(i)));
        }
        assertState(scrambled, "a mixed sequence and its inverse must cancel");
    }

    private static void oppositeFacesCommute() throws Exception {
        setState(labeledCube());
        apply(SCRAMBLE);
        int[][][] scrambled = copyState();
        for (String pair : new String[]{"FB", "Fb", "fB", "fb", "RL", "Rl", "rL", "rl",
                "DU", "Du", "dU", "du"}) {
            setState(copy(scrambled));
            apply(pair);
            int[][][] expected = copyState();
            setState(copy(scrambled));
            move(pair.charAt(1));
            move(pair.charAt(0));
            assertState(expected, "opposite faces must commute: " + pair);
        }
    }

    private static void solvedInitialization() throws Exception {
        INITIALIZE.invoke(null);
        check((Boolean) SOLVED.invoke(null), "initial cube must be solved");
        for (int face = 0; face < 6; face++) {
            for (int[] row : state()[face]) {
                for (int value : row) {
                    check(value == face, "each initial face must have its own uniform color");
                }
            }
        }
        move('F');
        check(!(Boolean) SOLVED.invoke(null), "one F must leave the cube unsolved");
        apply("FFF");
        check((Boolean) SOLVED.invoke(null), "four F turns must solve the cube again");
    }

    private static void commandLine() throws Exception {
        check("4\n2\n1\n105\n".equals(runMain("F\nFF\nFf\nFR\n")),
                "CLI must print only the repetition count for every input line");
        check("4\n".equals(runMain("R")), "CLI must read the final line without a newline");
        check("".equals(runMain("")), "empty input must terminate without output");
    }

    private static String runMain(String input) throws Exception {
        InputStream previousIn = System.in;
        PrintStream previousOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try (PrintStream capture = new PrintStream(output, true, "UTF-8")) {
            System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
            System.setOut(capture);
            Main3.main(new String[0]);
        } finally {
            System.setIn(previousIn);
            System.setOut(previousOut);
        }
        return output.toString("UTF-8").replace("\r\n", "\n");
    }

    private static Method method(Class<?> owner, String name, Class<?>... arguments)
            throws NoSuchMethodException {
        Method method = owner.getDeclaredMethod(name, arguments);
        method.setAccessible(true);
        return method;
    }

    private static int[][] rotate(int[][] matrix, boolean clockwise) throws Exception {
        return (int[][]) ROTATE.invoke(null, matrix, clockwise);
    }

    private static void move(char movement) throws Exception {
        for (Object candidate : MOVEMENTS.getEnumConstants()) {
            if (((Enum<?>) candidate).name().equals(String.valueOf(movement))) {
                MOVE.invoke(candidate);
                return;
            }
        }
        throw new AssertionError("Missing movement: " + movement);
    }

    private static void apply(String sequence) throws Exception {
        for (char movement : sequence.toCharArray()) {
            move(movement);
        }
    }

    private static char inverse(char movement) {
        return Character.isUpperCase(movement)
                ? Character.toLowerCase(movement) : Character.toUpperCase(movement);
    }

    private static int[][][] labeledCube() {
        int[][][] result = new int[6][3][3];
        for (int face = 0; face < 6; face++) {
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {
                    result[face][row][column] = face * 100 + row * 10 + column;
                }
            }
        }
        return result;
    }

    private static int[][][] state() throws IllegalAccessException {
        return (int[][][]) STATE.get(null);
    }

    private static void setState(int[][][] cube) throws IllegalAccessException {
        STATE.set(null, cube);
    }

    private static int[][][] copyState() throws IllegalAccessException {
        return copy(state());
    }

    private static int[][][] copy(int[][][] cube) {
        int[][][] result = new int[6][3][];
        for (int face = 0; face < 6; face++) {
            for (int row = 0; row < 3; row++) {
                result[face][row] = cube[face][row].clone();
            }
        }
        return result;
    }

    private static int[] sortedStickers(int[][][] cube) {
        int[] values = new int[54];
        int index = 0;
        for (int[][] face : cube) {
            for (int[] row : face) {
                for (int value : row) {
                    values[index++] = value;
                }
            }
        }
        Arrays.sort(values);
        return values;
    }

    private static void assertState(int[][][] expected, String message) throws Exception {
        for (int face = 0; face < 6; face++) {
            check(Arrays.deepEquals(expected[face], state()[face]), message + ", face " + face
                    + ": expected " + Arrays.deepToString(expected[face])
                    + ", got " + Arrays.deepToString(state()[face]));
        }
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
