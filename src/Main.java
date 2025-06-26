import java.util.Arrays;
import java.util.Stack;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int[] nums1={2,4};
        int[] nums2={1,2,3,4};
        System.out.println(Arrays.toString(nextGreaterElement(nums1,nums2)));

        String ch="(){}}{";
        System.out.println(isValid(ch));

        String s="abbaca";
        System.out.println(removeDuplicates(s));

        String s1="(())((()))";
        System.out.println(removeOuterParentheses(s1));
    }

    static int[] nextGreaterElement(int[] nums1, int[] nums2) {
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

}