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





const products = [
    {
        id: "1",
        name: "Ruiz Giuliano",
        post: "Propio",
        pname: "AF001NJ",
        priority: "Finalizado",
        pbg: "success.main",
        budget: "La Segunda"
    },
    {
        id: "2",
        name: "Rodriguez Olga",
        post: "Laboral",
        pname: "AA123FA",
        priority: "Cancelado",
        pbg: "error.main",
        budget: "Rivadavia"
    },
    {
        id: "3",
        name: "Lauren Paola",
        post: "Propio",
        pname: "GRF465",
        priority: "Atendido",
        pbg: "primary.main",
        budget: "Federacion Patronal"
    },
    {
        id: "4",
        name: "Navarro Federico",
        post: "Propio",
        pname: "OPD698",
        priority: "Finalizado",
        pbg: "success.main",
        budget: "Zurich"
    }
];
const ProductPerfomance = ()=>{
    const [rows, setRows] = (0,react__WEBPACK_IMPORTED_MODULE_1__.useState)([]);
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
                console.log(data);
                // Set the obtained data to the 'rows' state
                setRows(data);
            } catch (error) {
                console.error("Error al realizar la solicitud:", error);
            }
        };
        // Call the fetchData function
        fetchData();
    }, []);
    const getColorForEstado = (estado)=>{
        switch(estado){
            case "Sin atender":
                return "error.main"; // o el color que desees para Sin atender
            case "Atendido":
                return "primary.main"; // o el color que desees para Atendido
            case "Finalizado y Entregado":
                return "success.main"; // o el color que desees para Atendido y Finalizado
            default:
                return ""; // o el color por defecto si el estado no coincide con ninguno de los anteriores
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
                                        children: "Aseguradora"
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
                                                    })
                                                ]
                                            })
                                        })
                                    }),
                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableCell, {
                                        children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Typography, {
                                            color: "textSecondary",
                                            variant: "h6",
                                            children: new Date(row.fecha).toLocaleDateString("es-ES", {
                                                day: "2-digit",
                                                month: "2-digit",
                                                year: "numeric"
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
                                                backgroundColor: "success.main",
                                                color: "#fff"
                                            },
                                            size: "small",
                                            label: row.estado
                                        })
                                    }),
                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableCell, {
                                        align: "right",
                                        children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Typography, {
                                            variant: "h6",
                                            children: row.aseguradora
                                        })
                                    }),
                                    /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.TableCell, {
                                        children: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_material__WEBPACK_IMPORTED_MODULE_3__.Button, {
                                            startIcon: /*#__PURE__*/ react_jsx_runtime__WEBPACK_IMPORTED_MODULE_0__.jsx(_mui_icons_material_Edit__WEBPACK_IMPORTED_MODULE_4__/* ["default"] */ .Z, {}),
                                            color: "secondary",
                                            style: {
                                                margin: "-10px"
                                            },
                                            children: "Cambiar Estado"
                                        })
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