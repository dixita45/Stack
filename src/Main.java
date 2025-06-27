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

    static String infixToPostfix(String st)
    {
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

    static int priority(char c)
    {
        if (c == '^') return 3;
        else if (c == '/' || c == '*') return 2;
        else if (c == '+' || c == '-') return 1;
        else return -1;
    }

    static String infixToPrefix(String st)
    {
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

    static int[] nextGreaterElement(int[] arr)
    {
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



}