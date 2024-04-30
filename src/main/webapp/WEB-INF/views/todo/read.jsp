<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Todo Read</title>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">Navbar</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div class="navbar-nav">
                            <a class="nav-link active" aria-current="page" href="#">Home</a>
                            <a class="nav-link" href="#">Features</a>
                            <a class="nav-link" href="#">Pricing</a>
                            <a class="nav-link disabled">Disabled</a>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <!-- header end -->
    <!-- 기존의 <h1>Header</h1>끝 -->
    <div class="row content">
        <div class="col">
            <div class="card">

                <div class="card-header">
                    할 일
                </div>
                <div class="card-body">

                    <div class="input-group mb-3">
                        <span class="input-group-text">TNO</span>
                        <input type="text" name="tno" class="form-control"
                               value=<c:out value="${dto.tno}"></c:out> readonly>
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text">Title</span>
                        <input type="text" name="title" class="form-control"
                               value='<c:out value="${dto.title}"></c:out>' readonly>
                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text">DueDate</span>
                        <input type="date" name="dueDate" class="form-control"
                               value=<c:out value="${dto.dueDate}"></c:out> readonly>

                    </div>

                    <div class="input-group mb-3">
                        <span class="input-group-text">Writer</span>
                        <input type="text" name="writer" class="form-control"
                               value=<c:out value="${dto.writer}"></c:out> readonly>

                    </div>

                    <div class="form-check">

                        <label class="form-check-label" >

                            Finished &nbsp;

                        </label>

                        <input class="form-check-input" type="checkbox" name="finished" ${dto.finished?"checked":""} disabled >
                    </div>

                    <div class="my-4">

                        <div class="float-end">
<%--                                                            클래스가 두 가지인 경우 띄어쓰기 함--%>
                            <button type="button" class="btn btn-primary">  수정 / 삭제 </button>
                            <button type="button" class="btn btn-secondary"> 할일 목록으로 가기</button>

                        </div>

                    </div>

                </div>

            </div>



        </div>



    </div>

</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>

<script>
    //목록 페이지로 이동하는 이벤트 처리
    document.querySelector(".btn-secondary").addEventListener("click", function (event) {
        <%--console.log("read.jsp에서의 할일 목록으로 가기 눌렀을 때")--%>
        <%--console.log(${pageRequestDTO.link});--%>
        self.location=`/springex/todo/list?${pageRequestDTO.link}`
    }, false)

    //수정 / 삭제 페이지로 이동하는 이벤트 처리
    document.querySelector(".btn-primary").addEventListener("click", function (event) {
        <%--self.location="/springex/todo/modify?tno="+${dto.tno}--%>
        // 수정 삭제 페이지로 이동시 페이징 정보를 유지한 채로 가야 하게 끔 수정
        self.location=`/springex/todo/modify?tno=${dto.tno}&${pageRequestDTO.link}`
    }, false)

</script>