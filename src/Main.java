import com.sun.source.tree.WhileLoopTree;

import java.util.Arrays;
import java.util.Stack;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int[] nums1={2,4};
        int[] nums2={1,2,3,4};
        System.out.println(Arrays.toString(nextGreaterElementLC(nums1,nums2)));

        String ch="(){}}{";
        System.out.println(isValid(ch));

        String s="abbaca";
        System.out.println(removeDuplicates(s));

        String s1="(())((()))";
        System.out.println(removeOuterParentheses(s1));

        String post = "a+b*(c^d-e)^(f+g*h)-i";
        System.out.println(infixToPostfix(post));

        String pre = "a+b";
        System.out.println(infixToPrefix(pre));

        int[] arr={4,12,5,3,1,2,5,3,1,2,4,6};
        System.out.println(Arrays.toString(nextGreaterElement(arr)));

        int[] arr2={5,7,1,7,6,0};
        System.out.println(Arrays.toString(nextGreaterElement2(arr2)));

        int[] arr3={1, 5, 0, 3, 4, 5};
        System.out.println(Arrays.toString(nextSmallerElement(arr3)));

        int[] arr4={3,1,2,4};
        System.out.println(sumSubarrayMins(arr4));

        int[] arr5={3,1,2,4};
        System.out.println(sumSubarrayMaxs(arr5));

        int[] arr6={1,1};
        System.out.println(subArrayRanges(arr6));

        int[] asteroids={10,2,-5};
        System.out.println(Arrays.toString(asteroidCollision(asteroids)));

        String num="1432219";
        int digits=3;
        System.out.println(removeKdigits(num,digits));

    }

    static int[] nextGreaterElementLC(int[] nums1, int[] nums2) {
        int[] ans=new int[nums1.length];
        for(int i=0;i<nums1.length;i++)
        {
            ans[i]=-1;
        }
        for(int i=0;i<nums1.length;i++)
        {
            for(int j=0;j<nums2.length;j++) {
                if (nums1[i]==nums2[j])
                {
                    for(int k=j+1;k<nums2.length;k++)
                    {
                        if(nums2[k]>nums1[i])
                        {
                            ans[i]= nums2[k];
                            break;
                        }
                    }
                }

            }
        }
        return ans;
    }

    static boolean isValid(String s) {
    if(s.length()%2==1) return false;
    Stack<Character> st= new Stack<>();
    char[] ans=s.toCharArray();
        for(int i=0;i<ans.length;i++)
        {
            if(ans[i]=='(' || ans[i]=='{' || ans[i]=='[')
            {
                st.push(ans[i]);
            }
            else if( !(st.isEmpty()) && ((ans[i]==')' && st.peek()=='(') ||
               (ans[i]=='}' && st.peek()=='{') ||
                (ans[i]==']' && st.peek()=='[') ))
            {
                st.pop();
            }
            else{
                return false;
            }
        }
        return st.isEmpty();
    }

    static String removeDuplicates(String s) {
        Stack<Character> st = new Stack<>();
        for(char ch : s.toCharArray())
        {
           if(!(st.isEmpty()) && (st.peek()==ch))
           {
               st.pop();
           }
           else{
               st.push(ch);
           }
        }

        StringBuilder sb =new StringBuilder();
        for(char c :st)
        {
            sb.append(c);
        }
    return sb.toString();
    }

    static String removeOuterParentheses(String s) {
    StringBuilder ans=new StringBuilder();
    Stack<Character> st=new Stack<>();
    for(int i=0;i<s.length();i++)
    {
        if(st.isEmpty() && s.charAt(i)=='(')
        {
            st.push(s.charAt(i));
        }
        else{
            if(s.charAt(i)=='(')
            {
                st.push(s.charAt(i));
                ans.append(s.charAt(i));
            }
            else{
                st.pop();
                if(st.isEmpty())
                {
                    continue;
                }
                else{
                    ans.append(s.charAt(i));
                }
            }
        }
    }
    return ans.toString();
    }

    static String infixToPostfix(String st) {
        Stack<Character> stack = new Stack<>();
        String ans = "";
        char[] arr = st.toCharArray();
        for (int i = 0; i < arr.length; i++)
        {
            if ((arr[i] >= 'A' && arr[i] <= 'Z')
                    || (arr[i] >= 'a' && arr[i] <= 'z')
                    || (arr[i] >= '0' && arr[i] <= '9'))
            {
                ans += arr[i];
            }
            else if (arr[i] == '(')
            {
                stack.push(arr[i]);
            }
            else if (arr[i] == ')')
            {
                while (!stack.isEmpty() && stack.peek() != '(')
                {
                    ans += stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() == '(')
                {
                    stack.pop();
                }
                else
                {
                    return "Invalid Expression";
                }
            }
            else
            {
                while (!stack.isEmpty() && (priority(arr[i]) <= priority(stack.peek())))
                {
                    if (stack.peek() == '(') break;
                    ans += stack.pop();
                }
                stack.push(arr[i]);
            }
        }

        while (!stack.isEmpty())
        {
            if (stack.peek() == '(')
            {
                return "Invalid Expression";
            }
            ans += stack.pop();
        }
        return ans;
    }

    static int priority(char c) {
        if (c == '^') return 3;
        else if (c == '/' || c == '*') return 2;
        else if (c == '+' || c == '-') return 1;
        else return -1;
    }

    static String infixToPrefix(String st) {
        StringBuilder sb = new StringBuilder(st);
        st=sb.reverse().toString();
        Stack<Character> stack = new Stack<>();
        String ans = "";
        char[] arr = st.toCharArray();
        for (int i = 0; i < arr.length; i++)
        {
            if ((arr[i] >= 'A' && arr[i] <= 'Z')
                    || (arr[i] >= 'a' && arr[i] <= 'z')
                    || (arr[i] >= '0' && arr[i] <= '9'))
            {
                ans += arr[i];
            }
            else if (arr[i] == '(')
            {
                stack.push(arr[i]);
            }
            else if (arr[i] == ')')
            {
                while (!stack.isEmpty() && stack.peek() != '(')
                {
                    ans += stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() == '(')
                {
                    stack.pop();
                }
                else
                {
                    return "Invalid Expression";
                }
            }
            else
            {
                while (!stack.isEmpty() && (priority(arr[i]) <= priority(stack.peek())))
                {
                    if (stack.peek() == '(') break;
                    ans += stack.pop();
                }
                stack.push(arr[i]);
            }
        }

        while (!stack.isEmpty())
        {
            if (stack.peek() == '(')
            {
                return "Invalid Expression";
            }
            ans += stack.pop();
        }
         return new StringBuilder(ans).reverse().toString();
    }

    static int[] nextGreaterElement(int[] arr) {
        Stack<Integer> stack =new Stack<>();
        int[] ans= new int[arr.length];
        for(int i= arr.length-1;i>=0;i--)
        {
            if(stack.isEmpty())
            {
                ans[i]=-1;
                stack.push(arr[i]);
            }
            else if(stack.peek()>arr[i]){
                ans[i]=stack.peek();
                stack.push(arr[i]);
            }
            else
            {
                while((!stack.isEmpty()) && (stack.peek() <= arr[i]))
                {
                    stack.pop();
                }
                if(stack.isEmpty())
                {
                    ans[i]=-1;
                }
                else {
                    ans[i] = stack.peek();
                }
                stack.push(arr[i]);
            }
        }
        return ans;
    }

    static int[] nextGreaterElement2(int[] arr) {
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        int[] nge = new int[n];
        for (int i = (2 * n - 1); i >= 0; i--) {
            while ((!st.isEmpty()) && (st.peek() <= arr[i % n])) {
                st.pop();
            }
            if (i < n) {
                if (!st.isEmpty()) nge[i] = st.peek();
                else nge[i] = -1;
            }
            st.push(arr[i % n]);
        }
        return nge;
    }

    static int[] nextSmallerElement(int[] arr) {
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        int[] nge = new int[n];
        for (int i = 0; i < n; i++) {
            while(!st.isEmpty() && st.peek()>=arr[i]) {
                    st.pop();
            }
            if(st.isEmpty())
            {
                nge[i]=-1;
            }
            else if(st.peek()<arr[i]){
                nge[i]=st.peek();
            }
            st.push(arr[i]);
        }
        return nge;
    }

    static int sumSubarrayMins(int[] arr) {
    int n = arr.length;
    int mod = (int)1e9 + 7;

    int[] prev = new int[n];
    int[] next = new int[n];

    Stack<Integer> st1 = new Stack<>();
    Stack<Integer> st2 = new Stack<>();

    for (int i = 0; i < n; i++) {
        while (!st1.isEmpty() && arr[st1.peek()] > arr[i]) {
            st1.pop();
        }
        prev[i] = st1.isEmpty() ? (i + 1) : (i - st1.peek());
        st1.push(i);
    }

        for (int i = n-1; i >=0; i--) {
            while (!st2.isEmpty() && arr[st2.peek()] >= arr[i]) {
                st2.pop();
            }
            next[i] = st2.isEmpty() ? (n - i) : (st2.peek()- i);
            st2.push(i);
        }

    long sum = 0;
    for (int i = 0; i < n; i++) {
        long count = (long)prev[i] * next[i];
        sum = (sum + (arr[i] * count) % mod) % mod;
    }

    return (int)sum;
        }

    static int sumSubarrayMaxs(int[] arr) {
        int n = arr.length;
        int mod = (int)1e9 + 7;

        int[] prev = new int[n];
        int[] next = new int[n];

        Stack<Integer> st1 = new Stack<>();
        Stack<Integer> st2 = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!st1.isEmpty() && arr[st1.peek()] < arr[i]) {
                st1.pop();
            }
            prev[i] = st1.isEmpty() ? (i + 1) : (i - st1.peek());
            st1.push(i);
        }

        for (int i = n-1; i >=0; i--) {
            while (!st2.isEmpty() && arr[st2.peek()] <= arr[i]) {
                st2.pop();
            }
            next[i] = st2.isEmpty() ? (n - i) : (st2.peek()- i);
            st2.push(i);
        }

        long sum = 0;
        for (int i = 0; i < n; i++) {
            long count = (long)prev[i] * next[i];
            sum = (sum + (arr[i] * count) % mod) % mod;
        }

        return (int)sum;
    }

    static long subArrayRanges(int[] nums) {
        int mod = (int)1e9 + 7;
        long ans=(sumSubarrayMaxs(nums)-sumSubarrayMins(nums))% mod;
        return ans;
    }

    static int[] asteroidCollision(int[] asteroids) {
        if(asteroids.length==1) return asteroids;
        Stack<Integer> stack = new Stack<>();
        for (int asteroid : asteroids) {
            boolean destroyed = false;
            while (!stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {
                if (stack.peek() < -asteroid) {
                    stack.pop();
                    continue;
                } else if (stack.peek() == -asteroid) {
                    stack.pop();
                }
                destroyed = true;
                break;
            }
            if (!destroyed) {
                stack.push(asteroid);
            }
        }

        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }

        return result;
    }

    static String removeKdigits(String num, int k) {
       if(num.length()==k) return "0";
        Stack<Character> stack = new Stack<>();
        char[] ans=num.toCharArray();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (!stack.isEmpty() && k > 0 && stack.peek() > c) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }

        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }

        // Remove leading zeros
        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    

}