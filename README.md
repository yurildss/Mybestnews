# 🗞️ NewsNow — Leitura de notícias offline e inteligente (em desenvolvimento)

Um aplicativo de leitura de notícias que baixa automaticamente os artigos mais recentes sobre os temas preferidos do usuário.  
Mesmo sem conexão com a internet, você pode continuar lendo suas notícias — graças ao sistema de sincronização inteligente feito com **WorkManager**.

---

## 🚀 Funcionalidades

- 🔄 **Atualização automática:** O app busca notícias periodicamente em segundo plano usando o **WorkManager**.  
- 📚 **Leitura offline:** Todos os artigos baixados ficam disponíveis mesmo sem internet.  
- 🎯 **Personalização de temas:** O usuário escolhe os assuntos de interesse (tecnologia, esportes, política etc.).  
- 💾 **Armazenamento local otimizado:** Uso de banco de dados local para salvar e gerenciar as notícias.  
- 🖼️ **Interface moderna e limpa:** Design intuitivo, inspirado em jornais digitais.  

---

## 🧱 Arquitetura

O app segue uma arquitetura **moderna e escalável**, baseada nos princípios do Android Jetpack:

```
View (Compose UI)
│
├── ViewModel (StateFlow + Hilt)
│
├── Repository (Room + Retrofit)
│
└── WorkManager (atualização periódica de notícias)
```

---

## 🛠️ Tecnologias Utilizadas

- **Kotlin**  
- **Jetpack Compose**  
- **Hilt (Injeção de dependências)**  
- **Room (Persistência local)**  
- **Retrofit (Requisições HTTP)**  
- **WorkManager (Tarefas em segundo plano)**  
- **Material Design 3**

---

## 📱 Design

A interface segue um visual **2D minimalista**, com cores suaves e ícones vetoriais inspirados em jornais digitais.  
O ícone principal do app foi criado especialmente para o projeto:

![Ícone do App](A_flat,_vector-style_digital_icon_of_a_newspaper_i.png)

---

## ⚙️ Execução do Projeto

1. Clone este repositório:  
   ```bash
   git clone https://github.com/seu-usuario/NewsNow.git
   ```

2. Abra o projeto no **Android Studio** (versão Iguana ou superior).  

3. Configure sua **API Key de notícias** no arquivo `local.properties`:  
   ```properties
   NEWS_API_KEY=coloque_sua_chave_aqui
   ```

4. Execute o app em um emulador ou dispositivo físico.  

---

## 📦 Estrutura de Pastas

```
NewsNow/
├── app/
│   ├── data/
│   │   ├── local/ (Room Entities e DAO)
│   │   ├── remote/ (APIs Retrofit)
│   │   └── repository/
│   ├── ui/
│   │   ├── home/
│   │   ├── details/
│   │   └── components/
│   ├── worker/
│   └── di/
└── README.md
```

---

## 🌐 Futuras Melhorias

- 🔔 Notificações para novas notícias relevantes  
- 🌙 Tema escuro automático  
- 💬 Seção de comentários entre leitores  
- 🧠 Recomendação de artigos baseada em histórico de leitura (IA)

---

## 👨‍💻 Autor

Desenvolvido por **Yuri Lima**  
📧 *yurildss@hotmail.com*  
💼 [LinkedIn]([https://www.linkedin.com/in/seu-perfil](https://www.linkedin.com/in/yuri-lima-475352187/))  
🐙 [GitHub](https://github.com/yurildss)

---

⭐ *Se você gostou deste projeto, deixe uma estrela no repositório!*
