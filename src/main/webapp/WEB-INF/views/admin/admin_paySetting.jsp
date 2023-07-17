<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<c:import url="../headerFooterForm/headerFooterForm_adminHeader.jsp" />

<!-- 메인 컨텐츠 -->
<div class="container">
    <div class="row justify-content-center align-items-center" style="height: 100vh;">
        <div class="col-md-8">
            <div class="card">
                <div class="card-body text-center">
                    <h2 class="card-title">요금설정</h2>
                    <hr>
                    <form action="/update-prices" method="post">
                        <div class="row">
                            <div class="col-sm-6">
                                <h4 class="mt-4">사용권 요금</h4>
                                <div class="bg-light p-3 mb-4">
                                    <div class="form-group row">
                                        <label for="hourlyPrice1" class="col-sm-6 col-form-label">1시간 사용권:</label>
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <input type="text" class="form-control text-right" id="hourlyPrice1" name="hourlyPrice1" value="기존 값" placeholder="요금을 입력하세요" maxlength="20">
                                                <div class="input-group-append">
                                                    <span class="input-group-text">원</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="hourlyPrice2" class="col-sm-6 col-form-label">2시간 사용권:</label>
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <input type="text" class="form-control text-right" id="hourlyPrice2" name="hourlyPrice2" value="기존 값" placeholder="요금을 입력하세요" maxlength="20">
                                                <div class="input-group-append">
                                                    <span class="input-group-text">원</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="hourlyPrice3" class="col-sm-6 col-form-label">3시간 사용권:</label>
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <input type="text" class="form-control text-right" id="hourlyPrice3" name="hourlyPrice3" value="기존 값" placeholder="요금을 입력하세요" maxlength="20">
                                                <div class="input-group-append">
                                                    <span class="input-group-text">원</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="hourlyPrice5" class="col-sm-6 col-form-label">5시간 사용권:</label>
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <input type="text" class="form-control text-right" id="hourlyPrice5" name="hourlyPrice5" value="기존 값" placeholder="요금을 입력하세요" maxlength="20">
                                                <div class="input-group-append">
                                                    <span class="input-group-text">원</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="hourlyPrice50" class="col-sm-6 col-form-label">50시간 사용권:</label>
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <input type="text" class="form-control text-right" id="hourlyPrice50" name="hourlyPrice50" value="기존 값" placeholder="요금을 입력하세요" maxlength="20">
                                                <div class="input-group-append">
                                                    <span class="input-group-text">원</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="hourlyPrice100" class="col-sm-6 col-form-label">100시간 사용권:</label>
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <input type="text" class="form-control text-right" id="hourlyPrice100" name="hourlyPrice100" value="기존 값" placeholder="요금을 입력하세요" maxlength="20">
                                                <div class="input-group-append">
                                                    <span class="input-group-text">원</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <h4 class="mt-4">정기권 요금</h4>
                                <div class="bg-light p-3 mb-4">
                                    <div class="form-group row">
                                        <label for="monthlyPrice3" class="col-sm-6 col-form-label">정기권 3일:</label>
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <input type="text" class="form-control text-right" id="monthlyPrice3" name="monthlyPrice3" value="기존 값" placeholder="요금을 입력하세요" maxlength="20">
                                                <div class="input-group-append">
                                                    <span class="input-group-text">원</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="monthlyPrice7" class="col-sm-6 col-form-label">정기권 7일:</label>
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <input type="text" class="form-control text-right" id="monthlyPrice7" name="monthlyPrice7" value="기존 값" placeholder="요금을 입력하세요" maxlength="20">
                                                <div class="input-group-append">
                                                    <span class="input-group-text">원</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="monthlyPrice15" class="col-sm-6 col-form-label">정기권 15일:</label>
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <input type="text" class="form-control text-right" id="monthlyPrice15" name="monthlyPrice15" value="기존 값" placeholder="요금을 입력하세요" maxlength="20">
                                                <div class="input-group-append">
                                                    <span class="input-group-text">원</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label for="monthlyPrice30" class="col-sm-6 col-form-label">정기권 30일:</label>
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <input type="text" class="form-control text-right" id="monthlyPrice30" name="monthlyPrice30" value="기존 값" placeholder="요금을 입력하세요" maxlength="20">
                                                <div class="input-group-append">
                                                    <span class="input-group-text">원</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <h4 class="mt-4">사물함 요금</h4>
                                <div class="bg-light p-3 mb-4">
                                    <div class="form-group row">
                                        <label for="lockerPrice30" class="col-sm-6 col-form-label">30일 사물함 요금:</label>
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <input type="text" class="form-control text-right" id="lockerPrice30" name="lockerPrice30" value="기존 값" placeholder="요금을 입력하세요" maxlength="20">
                                                <div class="input-group-append">
                                                    <span class="input-group-text">원</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <h4 class="mt-4">룸 요금</h4>
                                <div class="bg-light p-3 mb-4">
                                    <div class="form-group row">
                                        <label for="roomPrice1" class="col-sm-6 col-form-label">1시간 룸 요금:</label>
                                        <div class="col-sm-6">
                                            <div class="input-group">
                                                <input type="text" class="form-control text-right" id="roomPrice1" name="roomPrice1" value="기존 값" placeholder="요금을 입력하세요" maxlength="20">
                                                <div class="input-group-append">
                                                    <span class="input-group-text">원</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary">변경</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 부트스트랩 및 jQuery 스크립트 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<!-- 메뉴 클릭 시 해당 페이지로 이동 -->
</body>
</html>
