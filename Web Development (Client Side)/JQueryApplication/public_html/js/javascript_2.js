$(function () {
    /* Au chargement de la page : alimenter la liste des tailles */
    $.each(['10px', '12px', '14px', '16px', '18px', '20px', '22px', '24px'],
            function (i, item) {
                $("#taille").append($('<option>', {value: item, text: item}));
            }
    );
    /* click sur Remplir : 
     - labels passent en gras,
     - alimenter tous les champs par une valeur par défaut
     - focus sur le nom */
    $(document).on("click", "#remplir", function () {
        $("label").css("font-weight", "bold");
        $("input[type=text]").val("Defaut");
        $("input[type=number]").val(30);
        $("#nom").focus();
    });
    /* click sur valider :
     - valider le contenu des champs nom et prénom (erreur si > à 10 caractères)
     - une message en rouge + italique s'affiche en dessous de la zone en erreur */
    $("#valider").click(function () {
        check($("#nom"));
        check($("#prenom"));
        /*if ($("#nom").val().length > 10 || $("#prenom").val().length > 10) {
         $("#phrase").append($("<span>", {
         text: "Le nom ou le prénom est trop long ! ",
         class: "error"
         }));
         } else {
         $("#phrase").empty();
         }*/
    });
    $("#afficher").click(function () {
        $("#phrase2").empty();
        $("#phrase2").append($('<span>', {
            text: $("#nom").val() + " " + $("#prenom").val() + " " + $("#age").val()
        })).css("font-size", $("#taille").val()).css("color", $("#couleur").val());
    });
    $("#age").blur(function () {
        if ($("#age").val() <= 18) {
            alert("Vous être trop jeune!");
        }
        if ($("#age").val() >= 65) {
            alert("Vous être trop vieux!");
        }
    });
});

function check(elem) {
    if (elem.val().length > 10 && $("#" + elem.attr("id") + "Erreur").length === 0) {
        $("#phrase").append($("<span>", {
            text: "Le " + elem.attr("name") + " est trop long ! ",
            class: "error",
            id: elem.attr("id") + "Erreur"
        }));
    } else if (elem.val().length <= 10 && $("#" + elem.attr("id") + "Erreur").length !== 0) {
        $("#" + elem.attr("id") + "Erreur").detach();
    }
}

