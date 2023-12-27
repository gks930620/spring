package com.study;

import com.study.member.vo.MemberVO;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class Main {

    public static void main(String[] args)  throws  Exception{
        //Reflection, Class
        //Class.     자바의 class중 하나입니다. String class, Class class 처럼
        //Class<FreeBoardVO> .  FreeBoardVO class의 정보를 가지고있는 Class class입니다.   Class<T>
        // 필드,메소드, 생성자 등에 관한 정보가 있습니다.  이걸 이용해 객체생성,메소드실행도 가능

        //Class clazzFree= Class.forName("com.study.free.vo.FreeBoardVO");
        Class clazzFree= MemberVO.class;
        //현재 class의 이름
        System.out.println("clazzFree 이름 : " + clazzFree.getName());
        System.out.println("clazzFree 간단이름 : " + clazzFree.getSimpleName());
        System.out.println("---------------------------------------------");

        //모든필드
        Field[] declaredFields = clazzFree.getDeclaredFields();    //declared랑 아닌거 : private도 접근 가능
        for(Field field : declaredFields){
            System.out.println("필드 이름 :"  + field.getName());
            System.out.println("필드 타입 이름 : " + field.getType().getName() );
            System.out.println("필드 타입 간단이름 : " + field.getType().getSimpleName() );
            System.out.println( );
        }

        System.out.println("---------------------------------------------");
        //모든 메소드
        Method[] declaredMethods = clazzFree.getDeclaredMethods();
        for(Method method : declaredMethods){
            System.out.println("메소드 이름 : " + method.getName());
            System.out.println("메소드 리턴 타입 : " + method.getReturnType().getSimpleName() );
            Parameter[] parameters = method.getParameters();
            if(parameters.length>0) { //아마도 set
                for(Parameter parameter : parameters){  //1개일테지만..
                    System.out.println("파라미터 이름 : " + parameter.getName() );
                    System.out.println("파라미터 타입 : " + parameter.getType().getSimpleName() );
                }
            }else{ //아마도 get + toString
                System.out.println("파라미터 없음");
            }
            System.out.println();
        }
        System.out.println("---------------------------------------------");
        //FreeBoardVO freeBoard= new FreeBoardVO();
        MemberVO memberVO =(MemberVO) clazzFree.newInstance();
        Method[] methods = clazzFree.getDeclaredMethods();

        //Arrays.stream(methods).filter(method -> method.getName().startsWith("set")).collect(Collectors.toList());

        for(Method method : methods){
            String methodName = method.getName();
            if(methodName.startsWith("set")){
                Parameter[] parameters = method.getParameters();
                if(parameters[0].getType().getSimpleName().equals("int")){
                    method.invoke(memberVO, 5);
                    //freeBoardVO.setBoNo(5);
                } else if (parameters[0].getType().getSimpleName().equals("String")) {
                    method.invoke(memberVO, "String 값");
                }
            }
        }

        System.out.println(memberVO);

    }



    public static Object setAllFileds( Class clazz) throws  Exception{
        Object o = clazz.newInstance();
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method : methods){
            String methodName = method.getName();
            if(methodName.startsWith("set")){
                Parameter[] parameters = method.getParameters();
                if(parameters[0].getType().getSimpleName().equals("int")){
                    method.invoke(o, 5);
                    //freeBoardVO.setBoNo(5);
                } else if (parameters[0].getType().getSimpleName().equals("String")) {
                    method.invoke(o, "String 값");
                }
            }
        }
        System.out.println(o);
        return o;
    }



}
