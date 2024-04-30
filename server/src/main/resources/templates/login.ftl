<!DOCTYPE html>
<html
    lang="en"
    dir="ltr"
    class="chrome"
>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="msapplication-TileColor" content="#ffffff">
        <meta name="theme-color" content="#ffffff">
        <link rel="stylesheet" href="/static/bootstrap/bootstrap.min.css">
        <title>Логин</title>

    </head>
    <body>
        <main
            id="main"
            class="main"
        >
            <div class="container">
                <div class="flex-center min-vh-100 py-6 row">
                    <div class="col-xxl-4 col-xl-5 col-lg-6 col-md-8 col-sm-10">
                        <a
                            class="text-decoration-none"
                            href="/"
                        >
                            <div class="d-flex flex-center fw-bolder fs-5 mb-4">
                                <img
                                    src="/static/logo.svg"
                                    alt="Logo"
                                    height="100"
                                    title="Некоторая организация"
                                />
                            </div>
                        </a>
                        <div class="card">
                            <div class="p-4 p-sm-5 card-body">
                                <div class="d-flex justify-content-center align-items-center mb-2">
                                    <h5>Вход</h5>
                                </div>
                                <form
                                    class="needs-validation"
                                    novalidate
                                    method="POST"
                                    action=""
                                >
                                    <div class="mb-3">
                                        <label
                                            for="email"
                                            class="form-label"
                                        >
                                            Логин
                                        </label>
                                        <input
                                            type="text"
                                            class="form-control <#if error?default("") != ""> is-invalid</#if>"
                                            id="email"
                                            name="username"
                                            placeholder="Введите свой логин"
                                            value="${username?default("")}"
                                            autofocus
                                        />
                                    </div>

                                    <div class="mb-3 form-password-toggle">
                                        <div class="d-flex justify-content-between">
                                            <label
                                                class="form-label"
                                                for="password"
                                            >
                                                Пароль
                                            </label>
                                        </div>
                                        <div class="input-group input-group-merge">
                                            <input
                                                type="password"
                                                id="password"
                                                class="form-control <#if error?default("") != ""> is-invalid</#if>"
                                                name="password"
                                                placeholder="&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;&#xb7;"
                                                aria-describedby="password"
                                            />
                                        </div>
                                    </div>

                                    <#if error?default("") != "">
                                        <div class="invalid-feedback d-block">${error}</div>
                                    </#if>
                                    <div class="alert errorMessage p-1 mb-0"></div>
                                    <div class="justify-content-between align-items-center row">
                                        <div class="col-auto">
                                            <div class="mb-0 form-check">
                                                <input
                                                    id="rememberMe"
                                                    name="remember"
                                                    type="checkbox"
                                                    class="form-check-input"
                                                >
                                                <label
                                                    for="rememberMe"
                                                    class="mb-0 text-700 form-check-label"
                                                >
                                                    Запомнить меня
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div>
                                        <button
                                            type="submit"
                                            color="primary"
                                            class="mt-3 w-100 btn btn-primary"
                                        >
                                            Войти
                                        </button>
                                    </div>
                                    <div class="mb-0"></div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>
