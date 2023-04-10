<%@tag import="java.util.List"%>
<%@tag import="modelClasses.Book"%>
<%@tag description="bookListAdmin" pageEncoding="UTF-8"%>

<% List<Book> bookList = (List) request.getAttribute("bookList"); %>

<% for (Book book : bookList) {%>
<tr>
    <td><%=book.getBook_id()%></td>
    <td><%=book.getPublication_date()%></td>
    <td><i class="material-icons"><%=book.isVisibility() ? "check" : "remove"%></i></td>
    <td>
        <a href="edit?book_id=<%=book.getBook_id()%>" class="btn-floating btn-small waves-effect waves-light green">
            <i class="material-icons">edit</i>
        </a>
        <a href="delete?book_id=<%=book.getBook_id()%>" class="btn-floating btn-small waves-effect waves-light red" onclick="return confirm('Are you sure?');">
            <i class="material-icons">delete</i>
        </a>
    </td>
</tr>
<% } %>
