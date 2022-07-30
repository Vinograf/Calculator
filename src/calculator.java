import java.text.DecimalFormat;
import java.util.Scanner;

public class calculator {
    public static void main(String[] arg) {
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();
        String spaceless = input.replace(" ", "");
        int lenght = spaceless.length();
        int operationtype = operationType(spaceless);
        int sightplace = sightPlace(spaceless);
        String first = first(spaceless, sightplace);
        String second = second(spaceless, sightplace, lenght);
        int firsttype = checkType(first);
        int secondtype = checkType(second);
        excpection(spaceless, operationtype, firsttype, secondtype, lenght);
        math(firsttype, secondtype, operationtype, first, second);
    }


    public static int operationType(String spaceless) {
        int operationtype = 0;
        int count = 0;
        int plus = spaceless.indexOf('+', count);
        int minus = spaceless.indexOf('-', count);
        int devid = spaceless.indexOf('/', count);
        int mult = spaceless.indexOf('*', count);
        if (plus != -1) {
            operationtype = 1;
            count = plus;
        }
        if (minus != -1) {
            operationtype = 2;
            count = minus;
        }
        if (devid != -1) {
            operationtype = 3;
            count = devid;
        }
        if (mult != -1) {
            operationtype = 4;
            count = mult;
        }
        plus = spaceless.indexOf('+', count + 1);
        minus = spaceless.indexOf('-', count + 1);
        devid = spaceless.indexOf('/', count + 1);
        mult = spaceless.indexOf('*', count + 1);
        count = 0;
        if (plus != -1) {
            operationtype = 1;
            count = 1;
        }
        if (minus != -1) {
            operationtype = 2;
            count = 1;
        }
        if (devid != -1) {
            operationtype = 3;
            count = 1;
        }
        if (mult != -1) {
            operationtype = 4;
            count = 1;
        }
        if (count > 0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор");
                throw new RuntimeException(e);
            }
        }
        return operationtype;

    }

    public static int sightPlace(String spaceless) {
        int sightplace = 0;
        int plus = spaceless.indexOf('+');
        int minus = spaceless.indexOf('-');
        int devid = spaceless.indexOf('/');
        int mult = spaceless.indexOf('*');
        int dot = spaceless.indexOf('.');
        int comma = spaceless.indexOf(',');
        if (dot != -1 | comma != -1) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Калькулятор работает только с целыми числами");
                throw new RuntimeException(e);
            }
        }
        if (plus != -1 && minus != -1 && devid != -1 && mult != -1) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Строка не является математической операцией");
                throw new RuntimeException(e);
            }
        }
        if (plus != -1) {
            sightplace = plus;
        }
        if (minus != -1) {
            sightplace = minus;
        }
        if (devid != -1) {
            sightplace = devid;
        }
        if (mult != -1) {
            sightplace = mult;
        }

        return sightplace;

    }

    public static String first(String spaceless, int sightplace) {
        String first = spaceless.substring(0, sightplace);
        return first;
    }

    public static String second(String spaceless, int sightplace, int lenght) {
        String second = spaceless.substring(sightplace + 1, lenght);
        return second;
    }

    public static int checkType(String arg) {

        int returntype = 0;
        int error = 0;
        char[] num = arg.toCharArray();
        int[] type;
        int lenght = num.length;
        if (lenght < 1) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Некорректная строка");
                throw new RuntimeException(e);
            }
        }
        type = new int[lenght];
        for (int i = 0; i < num.length; i++) {
            int ascii = num[i];
            if (Character.isDigit(num[i])) {
                type[i] = 1; // arabic
            }
            if (ascii == 88 || ascii == 86 || ascii == 73) {
                type[i] = 2; // romanic
            }
            if (!Character.isDigit(num[i]) & ascii != 86 & ascii != 88 & ascii != 73) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("Неккоректный ввод");
                    throw new RuntimeException(e);
                }
            }
        }
        if (lenght > 1) {
            for (int i = 0; i < type.length - 1; i++) {
                if (type[i] != type[i + 1]) {
                    error = 1;
                }
            }
        }
        if ((type[0] == 1) & (error == 0)) {
            returntype = 1;
        } else if ((type[0] == 2) & (error == 0)) {
            returntype = 2;
        } else if (error == 1) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Неккоректный ввод");
                throw new RuntimeException(e);
            }
        }
        return returntype;

    }


    public static int excpection(String spaceless, int operationtype, int firsttype, int secondtype, int lenght) {
        int error = 0;

        if (operationtype == 5) {
            error = 1;
        }
        if (firsttype != secondtype) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Используются одновременно разные системы счисления");
                throw new RuntimeException(e);
            }
        }
        if (lenght > 9) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Некорректная строка");
                throw new RuntimeException(e);
            }
        }
        if (lenght < 3) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("Некорректная строка");
                throw new RuntimeException(e);
            }
        }
        return error;
    }

    public static void math(int firsttype, int secondtype, int operationtype, String first, String second) {
        if (firsttype == secondtype) {
            if (firsttype == 1) {
                arabMath(first, second, operationtype);
            }
            if (firsttype == 2) {
                romanMath(first, second, operationtype);
            }

        }

    }

    public static float arabMath(String first, String second, int operationtype) {
        float result = 0;
        if (operationtype == 1) {
            result = (Float.parseFloat(first) + Float.parseFloat(second));
            DecimalFormat df = new DecimalFormat("#");
            System.out.println(df.format(result));
        }
        if (operationtype == 2) {
            result = (Float.parseFloat(first) - Float.parseFloat(second));
            DecimalFormat df = new DecimalFormat("#");
            System.out.println(df.format(result));
        }
        if (operationtype == 3) {
            result = (Float.parseFloat(first) / Float.parseFloat(second));
            DecimalFormat df = new DecimalFormat("#");
            result = result - (result % 1);
            System.out.println(df.format(result));
        }
        if (operationtype == 4) {
            result = (Float.parseFloat(first) * Float.parseFloat(second));
            DecimalFormat df = new DecimalFormat("#");
            System.out.println(df.format(result));
        }
        return result;
    }

    public static float romanMath(String first, String second, int operationtype) {
        float result = 0;
        float error = 0;
        float num1 = romanCheck(first);
        float num2 = romanCheck(second);
        if (operationtype == 1) {
            result = (num1 + num2);
            System.out.println(arabicToRoman(result));
        }
        if (operationtype == 2) {
            result = (num1 - num2);
            if (result < 1) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("В римской системе нет отрицательных чисел");
                    throw new RuntimeException(e);
                }
            }
            if (result >= 1) {
                System.out.println(arabicToRoman(result));
            }
        }
        if (operationtype == 3) {
            result = (num1 / num2);
            result = result - (result % 1);
            System.out.println(arabicToRoman(result));
        }
        if (operationtype == 4) {
            result = (num1 * num2);
            System.out.println(arabicToRoman(result));
        } else {
            error = 1;
        }
        return error;
    }

    public static float romanCheck(String opperand) {
        float num = 0;
        if (opperand.equals("I")) {
            num = 1;
        }
        if (opperand.equals("II")) {
            num = 2;
        }
        if (opperand.equals("III")) {
            num = 3;
        }
        if (opperand.equals("IV")) {
            num = 4;
        }
        if (opperand.equals("V")) {
            num = 5;
        }
        if (opperand.equals("VI")) {
            num = 6;
        }
        if (opperand.equals("VII")) {
            num = 7;
        }
        if (opperand.equals("VIII")) {
            num = 8;
        }
        if (opperand.equals("IX")) {
            num = 9;
        }
        if (opperand.equals("X")) {
            num = 10;
        }
        return num;
    }

    public static String arabicToRoman(float result) {
        String all = "";
        if (result > 0 && result < 20) {
            float a = result % 10;
            float b = (result % 100 - a);
            float c = result / 10 - (a / 10);
            for (int i = 0; i < c - 1; i++) {
                all = all.concat("X");
            }
            String first = romanConverter(b);
            String second = romanConverter(a);
            String onetwo = first.concat(second);
            all = all.concat(onetwo);
        }
        if (result >= 20 && result < 40) {
            float a = result % 10;
            float b = (result % 100 - a);
            float c = result / 10 - (a / 10);
            for (int i = 0; i < c; i++) {
                all = all.concat("X");
            }
            String first = romanConverter(b);
            String second = romanConverter(a);
            String onetwo = first.concat(second);
            all = all.concat(onetwo);
        }
        if (result >= 40 && result < 50) {
            float a = result % 10;
            float b = (result % 100 - a);
            float c = result / 10 - (a / 10);
            all = all.concat("XL");
            String first = romanConverter(b);
            String second = romanConverter(a);
            String onetwo = first.concat(second);
            all = all.concat(onetwo);
        }
        if (result >= 50 && result < 90) {
            float a = result % 10;
            float b = (result % 100 - a);
            float x = (result - 50 - a) / 10;
            all = all.concat("L");
            for (int i = 0; i < x; i++) {
                all = all.concat("X");
            }
            String first = romanConverter(b);
            String second = romanConverter(a);
            String onetwo = first.concat(second);
            all = all.concat(onetwo);
        }
        if (result >= 90 && result < 100) {
            all = all.concat("XC");
        }
        if (result == 100) {
            all = all.concat("C");
        }
        return all;
    }

    public static String romanConverter(float num) {
        String rom = "";
        if (num == 1) {
            rom = "I";
        }
        if (num == 2) {
            rom = "II";
        }
        if (num == 3) {
            rom = "II";
        }
        if (num == 4) {
            rom = "IV";
        }
        if (num == 5) {
            rom = "V";
        }
        if (num == 6) {
            rom = "VI";
        }
        if (num == 7) {
            rom = "VII";
        }
        if (num == 8) {
            rom = "VIII";
        }
        if (num == 9) {
            rom = "IX";
        }
        if (num == 10) {
            rom = "X";
        }
        return rom;
    }
}




