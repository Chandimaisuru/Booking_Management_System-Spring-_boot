
getAllBook ();

function saveBook (){
    let name =$('#exampleFormControlInput2').val();
    let type =$('#exampleFormControlInput3').val();
    let location =$('#exampleFormControlInput4').val();

    $.ajax({
        method:"POST",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/book/saveBook",
        async:true,
        data:JSON.stringify({
            "bookID":"",
            "bookName":name,
            "bookType" : type,
            "bookLocation":location
        }),

        success: function(data){
            alert("saved")
            getAllBook ();
        },
        error: function(xhr,exception){
            alert("Error")
        }
    })
}


function updateBook (){
    let id =$('#exampleFormControlInput1').val();
    let name =$('#exampleFormControlInput2').val();
    let type =$('#exampleFormControlInput3').val();
    let location =$('#exampleFormControlInput4').val();

    $.ajax({
        method:"PUT",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/book/updateBook",
        async:true,
        data:JSON.stringify({
            "bookID":id,
            "bookName":name,
            "bookType" : type,
            "bookLocation":location
        }),

        success: function(data){
            alert("updated")
            getAllBook ();
        },
        error: function(xhr,exception){
            alert("Error")
        }
    })
}

function deleteBook (){
    let id =$('#exampleFormControlInput1').val();
    

    $.ajax({
        method:"DELETE",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/book/deleteBook/"+id ,
        async:true,
        

        success: function(data){
            alert("deleted")
            getAllBook ();
        },
        error: function(xhr,exception){
            alert("Error")
        }
    })
}


function getAllBook (){
   
    
    $.ajax({
        method:"GET",
        contentType:"application/json",
        url:"http://localhost:8080/api/v1/book/getAllBook",
        async:true,
        

        success: function(data){
            if(data.code === "00"){
                $('#bookTable').empty();
                for(let book of data.content){
                    let id =book.bookID
                    let name =book.bookName
                    let type =book.bookType
                    let location = book.bookLocation

                    var row=`<tr><td>${id}</td><td>${name}</td><td>${type}</td><td>${location}</td></tr>`;
                    $('#bookTable').append(row);
                }
            }
        },
        error: function(xhr,exception){
            alert("Error")
        }
    })
}

$(document).ready(function () {
    $(document).on('click', '#bookTable tr', function () {
        var col0 = $(this).find('td:eq(0)').text();
        var col1 = $(this).find('td:eq(1)').text();
        var col2 = $(this).find('td:eq(2)').text();
        var col3 = $(this).find('td:eq(3)').text();

        $('#exampleFormControlInput1').val(col0);
        $('#exampleFormControlInput2').val(col1);
        $('#exampleFormControlInput3').val(col2);
        $('#exampleFormControlInput4').val(col3);

    })
})
