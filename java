   一、字符串与其他数据类型的转换中实例代码的编写，并打印查看输出结果


   public class StringConversion {  
    public static void main(String[] args) {  
        // 示例1：将字符串转换为整数  
        String strNumber = "123";  
        int number = Integer.parseInt(strNumber);  
        System.out.println("字符串 \"" + strNumber + "\" 转换为整数: " + number);  
  
        // 示例2：将整数转换为字符串  
        int anotherNumber = 456;  
        String strAnotherNumber = Integer.toString(anotherNumber);  
        System.out.println("整数 " + anotherNumber + " 转换为字符串: \"" + strAnotherNumber + "\"");  
  
        // 示例3：将字符串转换为浮点数（双精度）  
        String strDouble = "78.9";  
        double doubleValue = Double.parseDouble(strDouble);  
        System.out.println("字符串 \"" + strDouble + "\" 转换为双精度浮点数: " + doubleValue);  
  
        // 示例4：将双精度浮点数转换为字符串  
        double anotherDouble = 123.456;  
        String strAnotherDouble = Double.toString(anotherDouble);  
        System.out.println("双精度浮点数 " + anotherDouble + " 转换为字符串: \"" + strAnotherDouble + "\"");  
    }  
}


运行结果

字符串 "123" 转换为整数: 123  
整数 456 转换为字符串: "456"  
字符串 "78.9" 转换为双精度浮点数: 78.9  
双精度浮点数 123.456 转换为字符串: "123.456"

   二、定义String s1 = "25";String s2 = "36"计算两个String中数值的加



   public class StringAddition {  
    public static void main(String[] args) {  
        String s1 = "25";  
        String s2 = "36";  
  
        // 将字符串转换为整数  
        int num1 = Integer.parseInt(s1);  
        int num2 = Integer.parseInt(s2);  
  
        // 计算和  
        int sum = num1 + num2;  
  
        // 打印结果  
        System.out.println(" " + sum);  
    }  
}


运行结果61

