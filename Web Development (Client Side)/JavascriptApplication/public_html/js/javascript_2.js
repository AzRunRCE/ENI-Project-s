/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

window.onload = function () {
    var cuisines = ["10px", "11px", "12px", "13px", "14px", "15px", "16px", "17px", "18px", "19px", "20px"];
    var sel = document.getElementById('taille');
    for (var i = 0; i < cuisines.length; i++) {
        var opt = document.createElement('option');
        opt.innerHTML = cuisines[i];
        opt.value = cuisines[i];
        sel.appendChild(opt);
    }
};

document.addEventListener("DOMContentLoaded", function (event) {
    document.getElementById('remplir').addEventListener("click", remplir_EventListener);
    document.getElementById('valider').addEventListener("click", valider_EventListener);
    document.getElementById('afficher').addEventListener("click", afficher_EventListener);
    document.getElementById('age').addEventListener("blur", checkAge_EventListener);

});

function checkAge_EventListener()
{
    var age = document.getElementById('age').value;
    if (age > 65)
    {
        alert("Trop vieux!");
    } else if (age < 18)
    {
        alert("Trop jeune!");
    }

}

function remplir_EventListener() {
    var labs = document.getElementsByClassName('lab');
    for (var i = 0; i < labs.length; ++i) {
        var item = labs[i];
        item.style.fontWeight = "bold";
    }
    document.getElementById('nom').value = 'Jean';
    document.getElementById('prenom').value = 'Moulin';
    document.getElementById('age').value = '35';
    document.getElementById('taille').value = '16px';
    document.getElementById('couleur').value = 'red';
    document.getElementById('nom').focus();
}


function valider_EventListener() {

    if (document.getElementById('nom').value.length > 10 || document.getElementById('prenom').value.length > 10) {
        var para = document.createElement("i");

        para.style.color = "red";
        var node = document.createTextNode("Nom/Prénom de plus de 10 caractères!");
        para.appendChild(node);
        var element = document.getElementById("phrase");

        clearChildElm(document.getElementById("phrase2"));
        clearChildElm(document.getElementById("phrase"));
        element.insertBefore(para, element.firstChild);
    }

}

function clearChildElm(elm)
{
    while (elm.firstChild) {
        elm.removeChild(elm.firstChild);
    }
}

function afficher_EventListener() {

    var txt = document.getElementById('nom').value + " " +
            document.getElementById('prenom').value + " " + document.getElementById('age').value;
    var para = document.createElement("p");
    para.style.color = document.getElementById('couleur').value;
    para.style.fontSize = document.getElementById('taille').value;
    var node = document.createTextNode(txt);
    para.appendChild(node);
    var element = document.getElementById("phrase2");
    clearChildElm(document.getElementById("phrase2"));
    clearChildElm(document.getElementById("phrase"));
    element.insertBefore(para, element.firstChild);
}