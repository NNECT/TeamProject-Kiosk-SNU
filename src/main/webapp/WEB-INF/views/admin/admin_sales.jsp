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
                    <h2 class="card-title">스터디카페 관리자 페이지</h2>
                    <hr>
                    <a href="#">
                        <span class="h3">매출현황</span>
                    </a>
                    <div class="row mt-4">
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">누적사용시간</h5>
                                    <canvas id="timeChart"></canvas>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">챌린지 선택률 및 성공률</h5>
                                    <canvas id="challengeChart"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 부트스트랩 및 jQuery 스크립트 -->
<script src="<c:url value="/js/jquery-3.7.0.min.js"/>"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    const toRadians = (val) => val * Math.PI / 180;

    const timeCanvas = document.getElementById('timeChart');
    const challengeCanvas = document.getElementById('challengeChart');

    const timeChart = new Chart(timeCanvas, {
        type: 'line',
        data: {
            labels: ${seatTimesLabels},
            datasets: [{
                label: '누적사용시간',
                data: ${seatTimesData},
                fill: false,
                tension: 0.1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });

    const challengeChart = new Chart(challengeCanvas, {
        type: 'polarArea',
        data: {
            datasets: [{
                data: ${challengeSituationData}
            }],
            labels: [
                <c:forEach items="${challengeSituationLabels}" var="label" varStatus="status">
                "${label}"<c:if test="${!status.last}">,</c:if>
                </c:forEach>
            ]
        },
        options: {
            elements: {
                arc: {
                    angle: ${challengeSituationAngles}
                }
            }
        }
    });
</script>

<!-- 메뉴 클릭 시 해당 페이지로 이동 -->
</body>
</html>
