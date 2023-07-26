
new DataTable('#seatReportTable', {
    dom: '<"top">rt<"bottom"fp><"clear">',

    lengthChange: false,
    info: false,
    responsive: false,


    columns: [
        null,
        null,
        null,
        {orderSequence: ['desc']},
        null
    ],

    language: {
        zeroRecords: '검색 결과가 없습니다.',
        search : "검색 : ",
        paginate: {
            "next": "다음",
            "previous": "이전"
        },
    },
    initComplete: function () {
        this.api()
            .columns()
            .every(function () {
                let column = this;
                let title = column.footer().textContent;

                // Create input element
                let input = document.createElement('input');
                input.placeholder = title;
                column.footer().replaceChildren(input);

                // Event listener for user input
                input.addEventListener('keyup', () => {
                    if (column.search() !== this.value) {
                        column.search(input.value).draw();
                    }
                });
            });

    }
})
;
