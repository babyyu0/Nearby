<template>
    <div class="container mt-5">
        <div class="display-3 mb-5 text-center">회원 정보</div>
        <div class="row flex-column g-2">
            <div class="card col" style="width: 100%;">
                <div class="card-header text-start">기본 회원 정보</div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item d-flex">
                        <div class="text-start ms-5">아이디</div>
                        <div id="view-id" class="text-start ms-5">dbdud1999</div>
                    </li>
                    <li class="list-group-item d-flex align-items-center">
                        <div class="text-start ms-5">비밀번호</div>
                        <div id="view-id" class="text-start ms-5"><button class="btn btn-secondary">비밀번호 변경</button></div>
                    </li>
                    <li class="list-group-item d-flex">
                        <div class="text-start ms-5">아이디</div>
                        <div id="view-id" class="text-start ms-5">sdfdsfsd</div>
                    </li>
                    
                </ul>
            </div>
            <dl class="row col">
                <dt class="col-4 text-end mb-3">아이디</dt>
                <dd id="view-id" class="col-8"></dd>

                <dt class="col-4 text-end mb-3">이름</dt>
                <dd id="view-name" class="col-8"></dd>

                <dt class="col-4 text-end mb-3">이메일</dt>
                <dd id="view-email" class="col-8"></dd>

                <dt class="col-4 text-end mb-3">지역</dt>
                <dd class="col-8">
                    <dl class="row">
                        <dt class="col-2 text-end mb-3">도 · 시</dt>
                        <dd id="view-sido" class="col-10"></dd>
                        <dt class="col-2 text-end mb-3">시 · 군 · 구</dt>
                        <dd id="view-gugun" class="col-10"></dd>
                    </dl>
                </dd>

                <dd class="col-4"></dd>
                <dd class="col-8 text-end mb-3"><input type="button" class="btn btn-primary" value="수정"
                        onclick="location.href= '/member/update.html'"></dd>
            </dl>
        </div>
    </div>
</template>