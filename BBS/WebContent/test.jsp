<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<head>
<title>Document</title>
<style>
body {
   margin: 0;
   padding: 0;
   background: #eee;
   padding-top: 75px;
   padding-bottom: 75px;
}

* {
   box-sizing: border-box;
   -moz-box-sizing: border-box;
   -webkit-print-color-adjust: exact;
   color-adjust;
}

.print_page {
   width: 21cm;
   min-height: 29.7cm;
   margin: 0 auto;
   background: #fff;
}

.top, .bottom {
   text-align: center;
   position: fixed;
   left: 0;
   right: 0;
   height: 75px;
   padding: 1rem;
   color: white;
   background: teal;
   font-weight: bold;
   display: flex;
   justify-content: space-between;
   align-items: center;
}

.example_div{
   width: 210mm; 
   height: 297mm; 
   border: 1px black solid; 
   margin:auto; 
   font-size:100px; 
   text-align:center;
}
.top {
   top: 0;
}

.bottom {
   bottom: 0;
}

@page {
   size: A4;
   margin: 0;
}

@media print {
   html, body {
      width: 210mm;
      height: 297mm;
      padding: 0;
   }
   .print_page{
      margin: 0;
      border: initial;
      width: initial;
      min-height: initial;
      box-shadow: initial;
      background: #fff;
      page-break-after: always;
   }
   .top, .bottom {
      display: none !important;
   }
}
</style>
</head>
<body id="body">
   <header class="top">
      <a href="javascript:window.print()"> <input type="button"
         id="print-button" value="인쇄하기">
      </a>
   </header>
   
   <div class="print_page">
      <div class="example_div">
         1</div>
      <div class="example_div">
         2</div>
      <div class="example_div">
         3</div>
   </div>

   <footer class="bottom">
   </footer>
</body>
</html>