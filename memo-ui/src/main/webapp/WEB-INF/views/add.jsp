<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="../layouts/header.jsp"/>

<div class="row">

    <form:form action="#" method="post" class="full-size" commandName="memoItem">

        <fmt:formatDate value="${memoItem.createDate}" pattern="dd/MM/yyyy" var="defaultDate"/>


        <table class="table">
            <tr>
                <td>
                    <label class="place-right"><spring:message code="memo.form.category"/></label>

                </td>
                <td>
                    <div class="input-control select full-size">
                        <select name="category">
                            <c:forEach var="category" items="${memoCategory}">
                                <option value="${category}">${category.lable}</option>
                            </c:forEach>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="place-right"> <spring:message code="memo.form.short.description"/></label>
                </td>
                <td>

                    <div class="input-control text full-size">
                        <input type="text" name="shortDescription" placeholder="Short Description"
                               value="${memoItem.shortDescription}"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="place-right"> <spring:message code="memo.form.create.date"/></label>
                </td>
                <td>
                    <div class="input-control text full-size" data-role="datepicker" data-format="dd/mm/yyyy">
                        <input type="text" placeholder="Created date" name="createDate"
                               value="${defaultDate}">
                        <button class="button"><span class="mif-calendar"></span></button>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="place-right"> <spring:message code="memo.form.modified.date"/></label>
                </td>
                <td>
                    <div class="input-control text full-size" data-role="datepicker" data-format="dd/mm/yyyy">
                        <input type="text" placeholder="Last Modified date"
                               name="lastModifiedDate" value="${defaultDate}">
                        <button class="button"><span class="mif-calendar"></span></button>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="place-right"> <spring:message code="memo.form.content"/></label>
                </td>
                <td>
                    <div class="input-control textarea full-size">
                        <textarea name="description">${memoItem.description}</textarea>
                    </div>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <div class="input-control  place-right">
                        <button class="shortcut-button bg-white bg-active-darkBlue " type="reset">
                            <span class="icon mif-plus"></span>
                            <span class="title">Clear</span>
                            <span class="badge">x</span>
                        </button>
                        <button class="shortcut-button bg-cyan bg-active-darkBlue fg-white" type="submit">
                            <span class="icon mif-cross"></span>
                            <span class="title">Add</span>
                            <span class="badge">+</span>
                        </button>
                    </div>
                </td>
            </tr>
        </table>


    </form:form>
</div>

<jsp:include page="../layouts/footer.jsp"/>