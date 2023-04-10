<%@tag import="otherClass.Db"%>
<%@tag import="modelClasses.User"%>
<%@tag import="java.util.List"%>
<%@tag import="modelClasses.Book"%>
<%@tag description="bookList" pageEncoding="UTF-8"%>

<% List<Book> bookList = (List) request.getAttribute("bookList"); %>

<% for (Book book : bookList) {

    User bookUser = Db.userRepository().get(book.getUser_id());
    if (bookUser != null) {%>
<div class="col s12">
    <div class="card">
        <div class="card-content">
            <a href="detail?book_id=<%=book.getBook_id()%>"><%=book.getPublication_date()%></a>
            <p class="flow-text"><%=book.getTitle()%>"><%=book.getAutor()%></p>
        </div>
        <div class="card-action">
            <a href="profile?user_id=<%=book.getUser_id()%>"><%=bookUser.toString().toUpperCase()%></a>
        </div>
    </div>
</div>
<% } } %>