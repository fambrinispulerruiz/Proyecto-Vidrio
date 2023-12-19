"use strict";
exports.id = 229;
exports.ids = [229];
exports.modules = {

/***/ 12229:
/***/ ((__unused_webpack_module, __webpack_exports__, __webpack_require__) => {

/* harmony export */ __webpack_require__.d(__webpack_exports__, {
/* harmony export */   Z: () => (__WEBPACK_DEFAULT_EXPORT__)
/* harmony export */ });
/* harmony import */ var react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(56786);
/* harmony import */ var react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(18038);
/* harmony import */ var react__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(react__WEBPACK_IMPORTED_MODULE_1__);
/* harmony import */ var _mui_material__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(17421);
/* harmony import */ var _mui_material__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(_mui_material__WEBPACK_IMPORTED_MODULE_3__);
/* harmony import */ var _shared_DashboardCard__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(36434);
/* harmony import */ var _mui_icons_material_Edit__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(15922);





const ProductPerfomance = ()=>{
    const [rows, setRows] = (0,react__WEBPACK_IMPORTED_MODULE_1__.useState)([]);
    const [openEditModal, setOpenEditModal] = (0,react__WEBPACK_IMPORTED_MODULE_1__.useState)(false);
    const [selectedId, setSelectedId] = (0,react__WEBPACK_IMPORTED_MODULE_1__.useState)(null);
    const [selectedEstadoId, setSelectedEstadoId] = (0,react__WEBPACK_IMPORTED_MODULE_1__.useState)(null);
    const [formData, setFormData] = (0,react__WEBPACK_IMPORTED_MODULE_1__.useState)({
        id: "",
        nombreAsegurado: "",
        patente: "",
        estado: "",
        propio: "",
        aseguradora: "",
        fecha: "",
        hora: "",
        telefonoAsegurado: "",
        vidrio_id: "",
        vidrio_nombre: "",
        nro_siniestro: "",
        orden: "",
        observaciones: ""
    });
    const [modalAbierto, setModalAbierto] = (0,react__WEBPACK_IMPORTED_MODULE_1__.useState)(false);
    const [estadoSeleccionado, setEstadoSeleccionado] = (0,react__WEBPACK_IMPORTED_MODULE_1__.useState)(null);
    (0,react__WEBPACK_IMPORTED_MODULE_1__.useEffect)(()=>{
        // This function will be called when the component mounts
        const fetchData = async ()=>{
            try {
                const username = "sven";
                const password = "pass";
                const authHeader = "Basic " + btoa(username + ":" + password);
                const response = await fetch("http://localhost:8080/restful/services/simple.OrdenDeTrabajos/actions/verOrdenesDeTrabajo/invoke", {
                    method: "GET",
                    headers: {
                        "Content-Type": 'application/json;profile="urn:org.apache.isis"',
                        "Authorization": authHeader,
                        "accept": "application/json;profile=urn:org.apache.isis/v2;suppress=all"
                    }
                });
                const data = await response.json();
                const sortedData = data.sort((a, b)=>{
                    const estadoOrder = {
                        "Sin Atender": 1,
                        "Atendido": 2,
                        "Finalizado Y Entregado": 3
                    };
                    return estadoOrder[a.estado] - estadoOrder[b.estado];
                });
                // Set the sorted data to the 'rows' state
                setRows(sortedData);
            } catch (error) {
                console.error("Error al realizar la solicitud:", error);
            }
        };
        // Call the fetchData function
        fetchData();
    }, []);
    const getColorForEstado = (estado)=>{
        switch(estado){
            case "Sin Atender":
                return "error.main"; // o el color que desees para Sin atender
            case "Atendido":
                return "primary.main"; // o el color que desees para Atendido
            case "Finalizado Y Entregado":
                return "success.main"; // o el color que desees para Atendido y Finalizado
            default:
                return ""; // o el color por defecto si el estado no coincide con ninguno de los anteriores
        }
    };
    const handleCloseEditModal = ()=>{
        setOpenEditModal(false);
    };
    const handleOpenEditModal = async (row)=>{
        try {
            const username = "sven";
            const password = "pass";
            const authHeader = "Basic " + btoa(username + ":" + password);
            const response = await fetch(`http://localhost:8080/restful/objects/simple.OrdenDeTrabajo/${row.id}`, {
                method: "GET",
                headers: {
                    "Content-Type": 'application/json;profile="urn:org.apache.isis"',
                    "Authorization": authHeader,
                    "accept": "application/json;profile=urn:org.apache.isis/v2;suppress=all"
                }
            });
            if (response.ok) {
                const ordenData = await response.json();
                const vidrioID = ordenData.vidrio.href.split("/").pop();
                // Actualiza el estado con los datos que trae el fetch
                setFormData({
                    id: ordenData.id,
                    nombreAsegurado: ordenData.nombreAsegurado,
                    patente: ordenData.patente,
                    estado: ordenData.estado,
                    propio: ordenData.propio,
                    aseguradora: ordenData.aseguradora,
                    fecha: ordenData.fecha,
                    hora: ordenData.fecha,
                    telefonoAsegurado: ordenData.telefonoAsegurado,
                    vidrio_id: vidrioID,
                    vidrio_nombre: ordenData.vidrio.title,
                    nro_siniestro: ordenData.nroSiniestro,
                    orden: ordenData.orden,
                    observaciones: ordenData.observaciones
                });
                setSelectedId(row.id);
                setOpenEditModal(true);
            } else {
                console.error("Error al obtener los datos de la empresa");
            }
        } catch (error) {
            console.error("Error al realizar la solicitud:", error);
        }
    };
    const abrirModal = (idOrden)=>{
        setModalAbierto(true);
        setSelectedEstadoId(idOrden);
    };
    const cerrarModal = ()=>{
        setModalAbierto(false);
    };
    const handleCambioEstado = (nuevoEstado)=>{
        // Aquí puedes realizar el fetch para actualizar el estado en el backend
        // y luego cerrar el modal si es necesario.
        console.log(nuevoEstado);
        setEstadoSeleccionado(nuevoEstado);
    };
    const handleAceptar = async (nuevoEstado, idOrden)=>{
        try {
            const username = "sven";
            const password = "pass";
            const authHeader = "Basic " + btoa(username + ":" + password);
            const response = await fetch(`http://localhost:8080/restful/objects/simple.OrdenDeTrabajo/${idOrden}/actions/cambiarEstadoDeLaOrden/invoke`, {
                method: "PUT",
                headers: {
                    "Content-Type": 'application/json;profile="urn:org.apache.isis"',
                    "Authorization": authHeader,
                    "accept": "application/json;profile=urn:org.apache.isis/v2;suppress=all"
                },
                body: JSON.stringify({
                    estado: {
                        value: nuevoEstado
                    }
                })
            });
            // Recarga la página después de enviar los datos
            if (response.ok) {
                window.location.reload();
            } else {
                console.error("Error al enviar datos a la base de datos.");
            }
        } catch (error) {
            console.error("Error al realizar la solicitud:", error);
        }
    };
    return /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_shared_DashboardCard__WEBPACK_IMPORTED_MODULE_2__/* ["default"] */ .Z, {
        title: "Historico de Ordenes",
        children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableContainer, {
            sx: {
                width: {
                    xs: "274px",
                    sm: "100%"
                }
            },
            children: /*#__PURE__*/ (0,react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxs)(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Table, {
                "aria-label": "simple table",
                sx: {
                    whiteSpace: "nowrap",
                    mt: 2
                },
                children: [
                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableHead, {
                        children: /*#__PURE__*/ (0,react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxs)(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableRow, {
                            children: [
                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableCell, {
                                    align: "center",
                                    children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Typography, {
                                        color: "textSecondary",
                                        variant: "h6",
                                        children: "Id"
                                    })
                                }),
                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableCell, {
                                    align: "center",
                                    children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Typography, {
                                        color: "textSecondary",
                                        variant: "h6",
                                        children: "Vidrio"
                                    })
                                }),
                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableCell, {
                                    align: "center",
                                    children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Typography, {
                                        color: "textSecondary",
                                        variant: "h6",
                                        children: "Asegurado"
                                    })
                                }),
                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableCell, {
                                    align: "center",
                                    children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Typography, {
                                        color: "textSecondary",
                                        variant: "h6",
                                        children: "Fecha"
                                    })
                                }),
                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableCell, {
                                    align: "center",
                                    children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Typography, {
                                        color: "textSecondary",
                                        variant: "h6",
                                        children: "Patente"
                                    })
                                }),
                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableCell, {
                                    align: "center",
                                    children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Typography, {
                                        color: "textSecondary",
                                        variant: "h6",
                                        children: "Estado"
                                    })
                                }),
                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableCell, {
                                    align: "center",
                                    children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Typography, {
                                        color: "textSecondary",
                                        variant: "h6",
                                        children: "Acciones"
                                    })
                                })
                            ]
                        })
                    }),
                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableBody, {
                        children: rows.map((row)=>/*#__PURE__*/ (0,react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxs)(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableRow, {
                                children: [
                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableCell, {
                                        children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Typography, {
                                            fontSize: "15px",
                                            fontWeight: 500,
                                            children: row.id
                                        })
                                    }),
                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableCell, {
                                        children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Typography, {
                                            fontSize: "15px",
                                            fontWeight: 500,
                                            children: row.vidrio.title
                                        })
                                    }),
                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableCell, {
                                        children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Box, {
                                            display: "flex",
                                            alignItems: "center",
                                            children: /*#__PURE__*/ (0,react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxs)(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Box, {
                                                children: [
                                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Typography, {
                                                        variant: "h6",
                                                        fontWeight: 600,
                                                        children: row.nombreAsegurado
                                                    }),
                                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Typography, {
                                                        color: "textSecondary",
                                                        fontSize: "13px",
                                                        children: row.telefonoAsegurado
                                                    }),
                                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Typography, {
                                                        color: "textSecondary",
                                                        fontSize: "13px",
                                                        children: row.aseguradora
                                                    })
                                                ]
                                            })
                                        })
                                    }),
                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableCell, {
                                        children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Box, {
                                            display: "flex",
                                            alignItems: "center",
                                            children: /*#__PURE__*/ (0,react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxs)(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Box, {
                                                children: [
                                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Typography, {
                                                        color: "textSecondary",
                                                        variant: "h6",
                                                        children: new Date(row.fecha).toLocaleDateString("es-ES", {
                                                            day: "2-digit",
                                                            month: "2-digit",
                                                            year: "numeric"
                                                        })
                                                    }),
                                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Typography, {
                                                        color: "textSecondary",
                                                        fontSize: "13px",
                                                        children: new Date(row.fecha).toLocaleTimeString("es-ES", {
                                                            hour: "2-digit",
                                                            minute: "2-digit"
                                                        })
                                                    })
                                                ]
                                            })
                                        })
                                    }),
                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableCell, {
                                        children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Box, {
                                            display: "flex",
                                            alignItems: "center",
                                            children: /*#__PURE__*/ (0,react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxs)(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Box, {
                                                children: [
                                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Typography, {
                                                        variant: "h6",
                                                        fontWeight: 600,
                                                        children: row.patente
                                                    }),
                                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Typography, {
                                                        color: "textSecondary",
                                                        fontSize: "13px",
                                                        children: row.propio
                                                    })
                                                ]
                                            })
                                        })
                                    }),
                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableCell, {
                                        children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Chip, {
                                            sx: {
                                                pl: "4px",
                                                pr: "4px",
                                                backgroundColor: getColorForEstado(row.estado),
                                                color: "#fff"
                                            },
                                            size: "small",
                                            label: row.estado
                                        })
                                    }),
                                    /*#__PURE__*/ (0,react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxs)(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableCell, {
                                        children: [
                                            /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Button, {
                                                startIcon: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_icons_material_Edit__WEBPACK_IMPORTED_MODULE_4__/* ["default"] */ .Z, {}),
                                                color: "secondary",
                                                style: {
                                                    margin: "-10px"
                                                },
                                                onClick: ()=>handleOpenEditModal(row),
                                                children: "Ver Datos"
                                            }),
                                            /*#__PURE__*/ (0,react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxs)(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Dialog, {
                                                open: openEditModal,
                                                onClose: handleCloseEditModal,
                                                children: [
                                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.DialogTitle, {
                                                        children: "Datos de la Orden"
                                                    }),
                                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.DialogContent, {
                                                        sx: {
                                                            width: "600px",
                                                            textAlign: "center"
                                                        },
                                                        children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Grid, {
                                                            item: true,
                                                            xs: 12,
                                                            lg: 12,
                                                            children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_shared_DashboardCard__WEBPACK_IMPORTED_MODULE_2__/* ["default"] */ .Z, {
                                                                title: "Orden #" + formData.id + "",
                                                                children: /*#__PURE__*/ (0,react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxs)(react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.Fragment, {
                                                                    children: [
                                                                        /*#__PURE__*/ (0,react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxs)(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Stack, {
                                                                            spacing: 3,
                                                                            children: [
                                                                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TextField, {
                                                                                    id: "vidrio",
                                                                                    label: "Vidrio a colocar",
                                                                                    value: formData.vidrio_nombre,
                                                                                    InputProps: {
                                                                                        readOnly: true,
                                                                                        style: {
                                                                                            color: "darkslategray",
                                                                                            backgroundColor: "#f2f2f2",
                                                                                            cursor: "not-allowed"
                                                                                        }
                                                                                    }
                                                                                }),
                                                                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TextField, {
                                                                                    id: "fecha",
                                                                                    label: "Fecha de realizaci\xf3n",
                                                                                    type: "date",
                                                                                    variant: "outlined",
                                                                                    fullWidth: true,
                                                                                    value: formData.fecha ? formData.fecha.split(" ")[0] : "",
                                                                                    InputProps: {
                                                                                        readOnly: true,
                                                                                        style: {
                                                                                            color: "darkslategray",
                                                                                            backgroundColor: "#f2f2f2",
                                                                                            cursor: "not-allowed"
                                                                                        }
                                                                                    }
                                                                                }),
                                                                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TextField, {
                                                                                    id: "hora",
                                                                                    label: "Hora",
                                                                                    type: "time",
                                                                                    variant: "outlined",
                                                                                    fullWidth: true,
                                                                                    value: formData.hora ? formData.hora.split(" ")[1] : "",
                                                                                    InputProps: {
                                                                                        readOnly: true,
                                                                                        style: {
                                                                                            color: "darkslategray",
                                                                                            backgroundColor: "#f2f2f2",
                                                                                            cursor: "not-allowed"
                                                                                        }
                                                                                    }
                                                                                }),
                                                                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TextField, {
                                                                                    id: "nombreAsegurado",
                                                                                    label: "Nombre del Asegurado",
                                                                                    variant: "outlined",
                                                                                    fullWidth: true,
                                                                                    value: formData.nombreAsegurado,
                                                                                    InputProps: {
                                                                                        readOnly: true,
                                                                                        style: {
                                                                                            color: "darkslategray",
                                                                                            backgroundColor: "#f2f2f2",
                                                                                            cursor: "not-allowed"
                                                                                        }
                                                                                    }
                                                                                }),
                                                                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TextField, {
                                                                                    id: "telefonoAsegurado",
                                                                                    label: "Tel\xe9fono del Asegurado",
                                                                                    variant: "outlined",
                                                                                    fullWidth: true,
                                                                                    value: formData.telefonoAsegurado,
                                                                                    InputProps: {
                                                                                        readOnly: true,
                                                                                        style: {
                                                                                            color: "darkslategray",
                                                                                            backgroundColor: "#f2f2f2",
                                                                                            cursor: "not-allowed"
                                                                                        }
                                                                                    }
                                                                                }),
                                                                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TextField, {
                                                                                    fullWidth: true,
                                                                                    id: "aseguradora",
                                                                                    label: "Aseguradora",
                                                                                    name: "aseguradora",
                                                                                    value: formData.aseguradora,
                                                                                    InputProps: {
                                                                                        readOnly: true,
                                                                                        style: {
                                                                                            color: "darkslategray",
                                                                                            backgroundColor: "#f2f2f2",
                                                                                            cursor: "not-allowed"
                                                                                        }
                                                                                    }
                                                                                }),
                                                                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TextField, {
                                                                                    id: "nroSiniestro",
                                                                                    label: "Nro Siniestro",
                                                                                    variant: "outlined",
                                                                                    fullWidth: true,
                                                                                    value: formData.nro_siniestro,
                                                                                    InputProps: {
                                                                                        readOnly: true,
                                                                                        style: {
                                                                                            color: "darkslategray",
                                                                                            backgroundColor: "#f2f2f2",
                                                                                            cursor: "not-allowed"
                                                                                        }
                                                                                    }
                                                                                }),
                                                                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.FormControlLabel, {
                                                                                    control: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Checkbox, {
                                                                                        checked: formData.orden === "Si",
                                                                                        inputProps: {
                                                                                            readOnly: true,
                                                                                            style: {
                                                                                                color: "darkslategray",
                                                                                                backgroundColor: "#f2f2f2",
                                                                                                cursor: "not-allowed"
                                                                                            }
                                                                                        }
                                                                                    }),
                                                                                    label: "\xbfTrae orden del seguro?"
                                                                                }),
                                                                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TextField, {
                                                                                    id: "patente",
                                                                                    label: "Patente",
                                                                                    variant: "outlined",
                                                                                    fullWidth: true,
                                                                                    value: formData.patente,
                                                                                    InputProps: {
                                                                                        readOnly: true,
                                                                                        style: {
                                                                                            color: "darkslategray",
                                                                                            backgroundColor: "#f2f2f2",
                                                                                            cursor: "not-allowed"
                                                                                        }
                                                                                    }
                                                                                }),
                                                                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TextField, {
                                                                                    fullWidth: true,
                                                                                    id: "propio",
                                                                                    label: "Vehiculo",
                                                                                    variant: "outlined",
                                                                                    value: formData.propio,
                                                                                    InputProps: {
                                                                                        readOnly: true,
                                                                                        style: {
                                                                                            color: "darkslategray",
                                                                                            backgroundColor: "#f2f2f2",
                                                                                            cursor: "not-allowed"
                                                                                        }
                                                                                    }
                                                                                }),
                                                                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TextField, {
                                                                                    id: "observaciones",
                                                                                    label: "Observaciones",
                                                                                    multiline: true,
                                                                                    rows: 4,
                                                                                    variant: "outlined",
                                                                                    fullWidth: true,
                                                                                    value: formData.observaciones,
                                                                                    InputProps: {
                                                                                        readOnly: true,
                                                                                        style: {
                                                                                            color: "darkslategray",
                                                                                            backgroundColor: "#f2f2f2",
                                                                                            cursor: "not-allowed"
                                                                                        }
                                                                                    }
                                                                                }),
                                                                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TextField, {
                                                                                    fullWidth: true,
                                                                                    id: "estado",
                                                                                    label: "Estado de la Orden",
                                                                                    value: formData.estado,
                                                                                    InputProps: {
                                                                                        readOnly: true,
                                                                                        style: {
                                                                                            color: "darkslategray",
                                                                                            backgroundColor: "#f2f2f2",
                                                                                            cursor: "not-allowed"
                                                                                        }
                                                                                    }
                                                                                })
                                                                            ]
                                                                        }),
                                                                        /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx("br", {}),
                                                                        /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Button, {
                                                                            startIcon: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_icons_material_Edit__WEBPACK_IMPORTED_MODULE_4__/* ["default"] */ .Z, {}),
                                                                            color: "secondary",
                                                                            onClick: ()=>abrirModal(formData.id),
                                                                            children: "Cambiar Estado de la Orden"
                                                                        }),
                                                                        /*#__PURE__*/ (0,react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxs)(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Dialog, {
                                                                            open: modalAbierto,
                                                                            onClose: cerrarModal,
                                                                            children: [
                                                                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.DialogTitle, {
                                                                                    children: "Datos de la Orden"
                                                                                }),
                                                                                /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.DialogContent, {
                                                                                    children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Grid, {
                                                                                        children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_shared_DashboardCard__WEBPACK_IMPORTED_MODULE_2__/* ["default"] */ .Z, {
                                                                                            title: "Cambiar Estado",
                                                                                            children: /*#__PURE__*/ (0,react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxs)("div", {
                                                                                                style: {
                                                                                                    textAlign: "center",
                                                                                                    marginTop: "10px"
                                                                                                },
                                                                                                children: [
                                                                                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Button, {
                                                                                                        onClick: ()=>handleCambioEstado("Sin_Atender"),
                                                                                                        color: "secondary",
                                                                                                        variant: estadoSeleccionado === "Sin_Atender" ? "contained" : "outlined",
                                                                                                        style: {
                                                                                                            marginBottom: "10px"
                                                                                                        },
                                                                                                        children: "Sin Atender"
                                                                                                    }),
                                                                                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx("br", {}),
                                                                                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Button, {
                                                                                                        onClick: ()=>handleCambioEstado("Atendido"),
                                                                                                        color: "warning",
                                                                                                        variant: estadoSeleccionado === "Atendido" ? "contained" : "outlined",
                                                                                                        style: {
                                                                                                            marginBottom: "10px"
                                                                                                        },
                                                                                                        children: "Atendido"
                                                                                                    }),
                                                                                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx("br", {}),
                                                                                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Button, {
                                                                                                        onClick: ()=>handleCambioEstado("Finalizado_y_Entregado"),
                                                                                                        color: "success",
                                                                                                        variant: estadoSeleccionado === "Finalizado_y_Entregado" ? "contained" : "outlined",
                                                                                                        style: {
                                                                                                            marginBottom: "10px"
                                                                                                        },
                                                                                                        children: "Finalizado y Entregado"
                                                                                                    })
                                                                                                ]
                                                                                            })
                                                                                        })
                                                                                    })
                                                                                }),
                                                                                /*#__PURE__*/ (0,react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsxs)(_mui_material__WEBPACK_IMPORTED_MODULE_3__.DialogActions, {
                                                                                    children: [
                                                                                        /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Button, {
                                                                                            onClick: ()=>estadoSeleccionado !== null && handleAceptar(estadoSeleccionado, selectedEstadoId),
                                                                                            children: "Aceptar"
                                                                                        }),
                                                                                        /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Button, {
                                                                                            onClick: cerrarModal,
                                                                                            children: "Cancelar"
                                                                                        })
                                                                                    ]
                                                                                })
                                                                            ]
                                                                        })
                                                                    ]
                                                                })
                                                            })
                                                        })
                                                    }),
                                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.DialogActions, {
                                                        children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Button, {
                                                            onClick: handleCloseEditModal,
                                                            children: "Cerrar"
                                                        })
                                                    })
                                                ]
                                            })
                                        ]
                                    })
                                ]
                            }, row.id))
                    })
                ]
            })
        })
    });
};
/* harmony default export */ const __WEBPACK_DEFAULT_EXPORT__ = (ProductPerfomance);


/***/ })

};
;