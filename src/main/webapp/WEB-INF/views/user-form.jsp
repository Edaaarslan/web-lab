<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Form</title>
</head>
<body>
    <h1>${user.id == null ? 'Add User' : 'Edit User'}</h1>
    <form action="${user.id == null ? '/users' : '/users/update'}" method="post">
        <input type="hidden" name="id" value="${user.id}" />
        <label>Name:</label>
        <input type="text" name="name" value="${user.name}" /><br>
        <label>Email:</label>
        <input type="email" name="email" value="${user.email}" /><br>
        <label>Role:</label>
        <input type="text" name="role" value="${user.role}" /><br>
        <button type="submit">Save</button>
    </form>
</body>
</html>
