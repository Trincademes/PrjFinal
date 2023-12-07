document.getElementById('cadastroForm').addEventListener('submit', cadastrarJogo);
var result = 0;
function cadastrarJogo(event) {
    event.preventDefault();

    const name = document.getElementById('name').value;
    const genero = document.getElementById('genero').value;

    fetch('http://localhost:8080/final', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name,genero }),
    })
        .then(response => response.json())
        .then(data => {
            alert('Jogo cadastrado com sucesso!');
            document.getElementById('cadastroForm').reset();            
        })
        .catch(error => {
            console.error('Erro ao cadastrar jogo:', error);
        });
}
function pesquisarJogo() {
    const searchId = document.getElementById('searchId').value;

    fetch(`http://localhost:8080/final/${searchId}`)
        .then(response => {
            if (response.status === 404) {
                return Promise.reject('Jogo não encontrado');
                result = 0;
            }
            return response.json();
        })
        .then(data => {
            result = 1;
            document.getElementById('name').value = `${data.name}`;
            document.getElementById('genero').value = `${data.genero}`;
        })
        .catch(error => {
            console.error('Erro ao pesquisar jogo:', error);
            const resultadoPesquisa = document.getElementById('resultadoPesquisa');
            resultadoPesquisa.innerHTML = 'Jogo não encontrado.';
            

        });
}
function atualizarJogo() {
    pesquisarJogo();
    if (result == 1) {
        const name = document.getElementById('name').value;
        const genero = document.getElementById('genero').value;
        const searchId = document.getElementById('searchId').value;

        fetch(`http://localhost:8080/final/${searchId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ name, genero }),
        })
            .then(response => response.json())
            .then(data => {
                alert('Jogo atualizado com sucesso!');
                document.getElementById('cadastroForm').reset();                
            })
            .catch(error => {
                console.error('Erro ao atualizar jogo:', error);
            });
    } else {
        alert('ID não encontrado na base de dados. Nenhum jogo foi alterado. Favor pesquisar jogo a ser alterado !!!');
    }
}



function excluirJogo() {
    pesquisarJogo();
    if (result == 1) {
        const name = document.getElementById('name').value;
        const genero = document.getElementById('genero').value;
        const searchId = document.getElementById('searchId').value;

        fetch(`http://localhost:8080/final/${searchId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ name, genero }),
        })
            .then(response => response.json())
            .then(data => {
                alert('Jogo excluido com sucesso!');
                document.getElementById('cadastroForm').reset();                
            })
            .catch(error => {
                console.error('Erro ao Excluir jogo:', error);
            });
    } else {
        alert('ID não encontrado na base de dados. Nenhum jogo foi alterado. Favor pesquisar jogo a ser alterado !!!');
    }
}
