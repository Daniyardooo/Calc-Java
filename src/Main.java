import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input operation or 'stop': ");
        String str = in.nextLine();
        while(!str.equals("stop")) {
            str = str.replaceAll(" ", "");

            if (str.contains("+")) {
                doOperation("\\+", str);
            } else if (str.contains("-")) {
                doOperation("-", str);
            } else if (str.contains("/")) {
                doOperation("/", str);
            } else if (str.contains("*")) {
                doOperation("\\*", str);
            } else {
                throw new IllegalArgumentException("Входная строка не содержит оператора");
            }

            System.out.println("Input operation or 'stop': ");
            str = in.nextLine();
        }
    }

    static void doOperation (String operator, String inputStr) {
        String[] strs = inputStr.split(operator);
        if (strs.length == 2) {
            if (strs[0].matches("[0-9]+")) {
                try {
                    int a = Integer.parseInt(strs[0]);
                    int b = Integer.parseInt(strs[1]);
                    check(a, b);
                    int res = operate(a, b, operator);
                    System.out.println(res);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Используются одновременно разные системы счисления");
                }
            } else if (strs[0].matches("[IVXCLlivxc]+")){
                try {
                    int a = romaToArab(strs[0]);
                    int b = romaToArab(strs[1]);
                    check(a, b);
                    int res = operate(a, b, operator);
                    System.out.println(toRim(res));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Используются одновременно разные системы счисления");
                }
            }
            else {
                throw new IllegalArgumentException("Некорректные входные данные");
            }
        } else {
            throw new IllegalArgumentException("формат математической операции не удовлетворяет заданию");
        }
    }

    static int sum(int a, int b) {
        return a + b;
    }

    static int subst(int a, int b) {
        return a - b;
    }

    static int div(int a, int b) {
        return a / b;
    }

    static int mult(int a, int b) {
        return a * b;
    }

    static int operate(int a, int b, String operator) {
        switch (operator) {
            case "\\+" -> {
                return sum(a, b);
            }
            case "-" -> {
                return subst(a, b);
            }
            case "\\*" -> {
                return mult(a, b);
            }
            case "/" -> {
                return div(a, b);
            }
        }
        throw new IllegalArgumentException("Некорректная операция");
    }

    static void check(int a, int b) {
        if (a > 10 || a < 1) {
            throw new IllegalArgumentException("Превышено количество допустимых операнд!");
        } else if (b > 10 || b < 1) {
            throw new IllegalArgumentException("Превышено количество допустимых операнд!");
        }

    }

    static int romaToArab(String roma) {
        int res = 0;
        roma = roma.toUpperCase();
        if (roma.matches("[IVXCL]+")) {
            int[] array = new int[roma.length()];
            for (int i = 0; i < roma.length(); i++) {
                if (roma.charAt(i) == 'I') {
                    array[i] = 1;
                } else if (roma.charAt(i) == 'V') {
                    array[i] = 5;
                } else if (roma.charAt(i) == 'X') {
                    array[i] = 10;
                } else if (roma.charAt(i) == 'L') {
                    array[i] = 50;
                } else if (roma.charAt(i) == 'C') {
                    array[i] = 100;
                }
            }
            if (array.length == 1) {
                return array[0];
            }
            res = array[array.length - 1];
            for (int i = array.length - 1; i > 0; i--) {
                if (array[i] == array[i - 1]) {
                    res = res + array[i - 1];
                } else if (array[i] > array[i - 1]) {
                    res = res - array[i - 1];
                } else if (array[i] < array[i - 1]) {
                    res = array[i - 1] + res;
                }

            }
        } else {
            throw new NumberFormatException();
        }
        return res;


    }

    private static String toRim(int num) {
        if (num > 0) {
            StringBuilder res = new StringBuilder();
            while (num > 0) {
                if (num >= 100) {
                    res.append('C');
                    num = num - 100;
                } else if (num >= 90) {
                    res.append("LC");
                    num = num - 90;
                } else if (num >= 50) {
                    res.append("L");
                    num = num - 50;
                } else if (num >= 40) {
                    res.append("XL");
                    num = num - 40;
                } else if (num >= 10) {
                    res.append("X");
                    num = num - 10;
                } else if (num >= 9) {
                    res.append("IX");
                    num = num - 9;
                } else if (num >= 5) {
                    res.append("V");
                    num = num - 5;
                } else if (num >= 4) {
                    res.append("IV");
                    num = num - 4;
                } else if (num >= 1) {
                    res.append("I");
                    num = num - 1;
                }
            }
            return res.toString();
        }
        throw new IllegalArgumentException("В римской системе нет отрицательных чисел и нуля");
    }


}

