<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:set var="menuActive" value="<%=request.getParameter("menuActive")%>"/>

<html>
<head>
    <title></title>
    <link rel="stylesheet" href="../resources/css/metro.css"/>
    <link rel="stylesheet" href="../resources/css/metro-icons.css"/>
    <link rel="stylesheet" href="../resources/css/metro-rtl.css"/>
    <link rel="stylesheet" href="../resources/css/metro-responsive.css"/>
    <link rel="stylesheet" href="../resources/css/metro-schemes.css"/>
    <link rel="stylesheet" href="../resources/css/style.css"/>
</head>
<body>
<div class="flex-grid">

    <div class="app-bar bg-blue  padding20 no-padding-top no-padding-bottom no-padding-right">
        <h2 class="leader">Memo
            <small> Organize notes</small>
        </h2>
    </div>

    <ul class="sidebar2">
        <li class="title">
            <span class="mif-home mif-2x   padding5 no-padding-bottom no-padding-top no-padding-left"></span>
            Memo
        </li>
        <li class="${menuActive=='home'?'active':''}">
            <a href="/home?menuActive=home">
            <span class="mif-home padding5 no-padding-bottom no-padding-top no-padding-left"></span>
            Home
            </a>
        </li>
        <li>
            <a href="#">
            <span class="mif-spinner mif-ani-pulse  padding5 no-padding-bottom no-padding-top no-padding-left"></span>
            Search
            </a>
        </li>
        <li class="${menuActive=='add'?'active':''}">
            <a href="/add?menuActive=add">
            <span class="mif-plus padding5 no-padding-bottom no-padding-top no-padding-left"></span>
                New
            </a>
        </li>
        <li>
            <a href="#">
            <span class="mif-wrench  padding5 no-padding-bottom no-padding-top no-padding-left"></span>
                Settings
            </a>
        </li>
    </ul>